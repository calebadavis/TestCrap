package com.example.testcrap;

public class Outside implements Environment {

    @Override
    public int getTemperature() { return outsideTemp; }

    @Override
    public void setTemperature(int temp) { outsideTemp = temp; }

    public static class Inside implements Environment {

        @Override
        public int getTemperature() { return insideTemp; }

        @Override
        public void setTemperature(int temp) { insideTemp = temp; }

        public void setThermostatSetting(int thermostat) { thermostatSetting = thermostat; }

        private int insideTemp;
        private int thermostatSetting;
    }

    private int outsideTemp;
}
