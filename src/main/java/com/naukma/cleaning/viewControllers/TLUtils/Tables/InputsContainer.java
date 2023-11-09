package com.naukma.cleaning.viewControllers.TLUtils.Tables;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class InputsContainer{

    private List<InputData> inputs;

    public InputsContainer(List<InputData> inputs) {
        this.inputs = inputs;
    }

    public List<InputData> getInputs() {
        return inputs;
    }

    @AllArgsConstructor
    public static class InputData{
        public String type;
        public String placeholder;
        public String name;
        public boolean required;
    }
}

