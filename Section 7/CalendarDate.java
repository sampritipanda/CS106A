public class CalendarDate {
	
	public CalendarDate(int month, int date, int year){
		this.month = month;
		this.date = date;
		this.year = year;
	}
	
	public int getMonth(){
		return month; 
	}
	
	public int getDate(){
		return date; 
	}
	
	public int getYear(){
		return year; 
	}
	
	public int compareTo(CalendarDate date2){
		CalendarDate date1 = this;
		if(date1.getYear() > date2.getYear()){
			return 1;
		}
		else if(date1.getYear() < date2.getYear()){
			return -1;
		}
		else {
			if(date1.getMonth() > date2.getMonth()){
				return 1;
			}
			else if(date1.getMonth() < date2.getMonth()){
				return -1;
			}
			else {
				if(date1.getDate() > date2.getDate()){
					return 1;
				}
				else if(date1.getDate() < date2.getDate()){
					return -1;
				}
				else {
					return 0;
				}
			}
		}
	}
	
	public String toString(){
		return (monthName[month - 1] + " " + date + ", " + year);
	}
	
	private int month, date, year;
	private String[] monthName = {"January", "February", "March", "April", "May", 
			"June", "July", "August", "September", "October", "November", "December"};
}
