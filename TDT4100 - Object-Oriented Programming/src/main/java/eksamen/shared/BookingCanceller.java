package eksamen.shared;

import java.util.List;

// An interface to represent a booking canceller
public interface BookingCanceller {

 /**
   * A method to cancel some bookings from a list
   * @param bookings the list of bookings to cancel from
   * @param numberToCancel the number of bookings to cancel
   */   
   public void cancelBookings(List<IBooking> bookings, int numberToCancel);
  
  }