package Model;

public class Client implements Comparable{
   private int ID;
   private int arrivalTime;
   private int serviceTime;

   public Client(int ID, int arrivalTime, int serviceTime){
      this.ID = ID;
      this.arrivalTime = arrivalTime;
      this.serviceTime = serviceTime;
   }

   @Override
   public String toString() {
      return  "(" + ID +
              "," + arrivalTime +
              "," + serviceTime +
              ')';
   }

   public int getID() {
      return ID;
   }

   public void setID(int ID) {
      this.ID = ID;
   }

   public int getArrivalTime() {
      return arrivalTime;
   }

   public void setArrivalTime(int arrivalTime) {
      this.arrivalTime = arrivalTime;
   }

   public int getServiceTime() {
      return serviceTime;
   }

   public void setServiceTime(int serviceTime) {
      this.serviceTime = serviceTime;
   }
   public void decreaseServiceTime(){
      this.serviceTime=getServiceTime()-1;
   }

   @Override
   public int compareTo(Object o) {
      Client client=(Client) o;
      return arrivalTime-client.getArrivalTime();
   }
}
