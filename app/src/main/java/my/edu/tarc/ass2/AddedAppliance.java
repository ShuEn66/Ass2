package my.edu.tarc.ass2;

public class AddedAppliance {
    private String appliance;
    private Double appUsage;
    private Double appCost;

    public AddedAppliance(String appliance){
        this.appliance = appliance;
    }

    public AddedAppliance(String appliance, Double usage, Double cost){
        this.appliance = appliance;
        this.appUsage = usage;
        this.appCost = cost;
    }

    public String getAppliance(){
        return appliance;
    }

    public Double getAppUsage(){
        return appUsage;
    }

    public Double getAppCost(){
        return appCost;
    }

    public void setAppliance(String appliance) {
        this.appliance = appliance;
    }

    public void setAppUsage(Double usage) {
        this.appUsage = usage;
    }

    public void setAppCost(Double cost) {
        this.appCost = cost;
    }
}