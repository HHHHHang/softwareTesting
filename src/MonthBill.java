public class MonthBill {
    private MonthInfo monthInfo = null;//当月缴费信息
    private double lastYearBill = 0;//去年未缴费金额
    private int currentTimes = 0;//本年度累计未缴费次数
    private boolean dataError = false;
    private double total = 0;//当月缴费金额

    //Constructor
    public MonthBill(){
        this.monthInfo = new MonthInfo();
        this.lastYearBill = 0;
        this.currentTimes = 0;
        this.total = 0;
        this.dataError = false;
    }

    public MonthBill(double lastYearBill, int currentTimes, int minutes){
        this.monthInfo = new MonthInfo(minutes);
        this.lastYearBill = lastYearBill;
        this.currentTimes = currentTimes;
        this.dataError = false;
        if(currentTimes < 0 || currentTimes > 11 || lastYearBill < 0){
            this.dataError = true;
        }
        if(this.monthInfo.isDataError()){
            this.dataError = true;
        }
        this.total = getMonthBill();
    }

    public double getMonthBill(){
        if(this.dataError){
            return -1;
        }
        if(currentTimes > this.monthInfo.getMaxTimes()){//超过最大容许次数则没有折扣
            this.monthInfo.setDiscount(1);
        }
        double temp =  monthInfo.getBasicCost() + monthInfo.getCostPerMin() * monthInfo.getMinutes() * monthInfo.getDiscount() + lastYearBill * 0.05;
        return (Math.round(temp * 1000) / 1000.0);
        //return Double.parseDouble(String.format("%.12f",temp));
    }



    public MonthInfo getMonthInfo() {
        return monthInfo;
    }

    public void setMonthInfo(MonthInfo monthInfo) {
        this.monthInfo = monthInfo;
    }

    public double getLastYearBill() {
        return lastYearBill;
    }

    public void setLastYearBill(double lastYearBill) {
        this.lastYearBill = lastYearBill;
    }

    public int getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(int currentTimes) {
        this.currentTimes = currentTimes;
    }

    public boolean isDataError() {
        return dataError;
    }

    public void setDataError(boolean dataError) {
        this.dataError = dataError;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
