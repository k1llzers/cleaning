package com.naukma.cleaning.batch;

import com.naukma.cleaning.dao.ReportDao;
import com.naukma.cleaning.dao.entities.ReportEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StatisticsWriter implements ItemWriter<ReportEntity> {
    @Autowired
    private ReportDao reportDao;

    @Override
    public void write(Chunk<? extends ReportEntity> chunk) throws Exception {
        if (chunk == null || chunk.getItems() == null || chunk.getItems().size() == 0) {
            log.error("SOMETHING WRONG WITH CHUNK");
        }
        log.info("BATCH: writing orders report for {}", chunk.getItems().get(0).getPeriod());
        reportDao.save(chunk.getItems().get(0));
        log.info("End of BATCH EXECUTION");
    }
}