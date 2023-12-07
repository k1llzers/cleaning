package com.naukma.cleaning;

import com.naukma.cleaning.models.dtos.CommentDto;
import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.commentService.CommentService;
import com.naukma.cleaning.services.orderService.OrderService;
import com.naukma.cleaning.services.proposalService.CommercialProposalServiceImpl;
import com.naukma.cleaning.services.userService.UserServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashSet;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class CleaningApplication {

//    @Autowired
//    private DataSource batchDataSource;
//
//    @Bean
//    public Step step(JobRepository jobRepository) {
//        StepBuilder stepBuilderOne = new StepBuilder("step1", jobRepository);
//        return stepBuilderOne.tasklet(helloWorldTasklet(), transactionManager())
//                .build();
//    }
//
//    @Bean
//    public Job job(JobRepository jobRepository) {
//        return new JobBuilder("job", jobRepository)
//                .start(step(jobRepository))
//                .build();
//    }
//
//    @Bean
//    public CompletionPolicy completionPolicy() {
//        CompositeCompletionPolicy policy =
//                new CompositeCompletionPolicy();
//        policy.setPolicies(
//                new CompletionPolicy[] {
//                        new TimeoutTerminationPolicy(3),
//                        new SimpleCompletionPolicy(1000)});
//        return policy;
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(batchDataSource);
//    }
//
//    @Bean
//    public Tasklet helloWorldTasklet() {
//        return (StepContribution contribution, ChunkContext chunkContext) -> {
//            System.out.println("Hello, World!");
//            return RepeatStatus.FINISHED;
//        };
//    }

    public static void main(String[] args) {
        var context = SpringApplication.run(CleaningApplication.class, args);
        CommercialProposal prop = new CommercialProposal();
        prop.setId(1);
        prop.setName("Super proposal");
        prop.setPrice(100.0);
        prop.setDescription("This is description of proposal");
        ((CommercialProposalServiceImpl)context.getBean("commercialProposalServiceImpl")).createCommercialProposal(prop);
        for(int i = 2; i <= 6; i++) {
            CommercialProposal prop1 = new CommercialProposal();
            prop1.setId(i);
            prop1.setName("Super proposal" + i);
            prop1.setPrice(i * 100.0);
            prop1.setDescription("This is description of proposal" + i);
            ((CommercialProposalServiceImpl) context.getBean("commercialProposalServiceImpl")).createCommercialProposal(prop1);
        }

        User user1 = new User();
        user1.setId(1);
        user1.setName("Jonathan");
        user1.setEmail("google@gmail.com");
        user1.setPassword("qwerty");
        user1.setRole(Role.User);
        User user2 = new User();
        user2.setId(2);
        user2.setName("Michael");
        user2.setEmail("google2@gmail.com");
        user2.setPassword("qwerty123");
        user2.setRole(Role.User);
        User user3 = new User();
        user3.setId(3);
        user3.setName("admin");
        user3.setEmail("admin");
        user3.setPassword("admin");
        user3.setRole(Role.Admin);
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);


        var userDTO = userService.createUser(new UserDto("name 0", "p@SsW0rd", "ema@e.ukma", Role.User));
        var user = userService.getUser(2);
        var addressservice = (AddressService)context.getBean("addressServiceImpl");
        var address = new Address();
        address.setId(1);
        address.setCity("Kyiv");
        address.setStreet("KPI");
        address.setHouseNumber("8d");
        address.setFlatNumber("7");
        addressservice.createAddress(user, address);
        userService.createUser(new UserDto("name 1e", "p@SsW1rd", "ex@e.edu", Role.Employee));
        var exec = userService.getUser(4);
        var execs = new HashSet<User>();
        execs.add(exec);
        OrderService orderService = (OrderService)context.getBean("orderServiceImpl");
        orderService.createOrder(new OrderDto(1, 405.0, LocalDateTime.now(), LocalDateTime.now().plusHours(1),
            user, execs, null, address,
            com.naukma.cleaning.models.order.Status.NOT_VERIFIED, new HashSet<CommercialProposal>()));
//        System.out.println(orderService.getOrder(1).toString());
//        System.out.println("It's alive!");
        CommentService commentService = (CommentService)context.getBean("commentServiceImpl");
        CommentDto comment = new CommentDto(1, "Text", LocalDateTime.now(), 5);
        //commentService.createComment(1l, comment);
//        System.out.println(orderService.getOrder(1).toString());
        comment.setText("ayayay");
        //commentService.editComment(comment);
//        System.out.println(orderService.getOrder(1).toString());
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Job job;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Scheduled(fixedRate = 2000)
    public void launchJob() throws Exception {
        Date date = new Date();
//        logger.debug("scheduler starts at " + date);
//        if (enabled.get()) {
            JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder().addDate("launchDate", date)
                    .toJobParameters());
//            batchRunCounter.incrementAndGet();
//            logger.debug("Batch job ends with status as " + jobExecution.getStatus());
//        }
//        logger.debug("scheduler ends ");
    }
//    @Scheduled(cron = "0/10 * * * * *") // define schedule as needed
//    public void runJob(@Autowired JobLauncher jobLauncher, @Autowired Job job) throws Exception {
//        JobParameters parameters = new JobParameters();
//        // add parameters as needed
//        jobLauncher.run(job, parameters);
//    }
}
