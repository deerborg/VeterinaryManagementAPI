package art.dborg.vetapp.common.utilities;

public class Message {
    public static final String NOT_FOUND_ID = "Not found ID"; // General message for objects not found
    public static final String NOT_FOUND_CUSTOMER = "Not found Customer ID"; // Error message for Customer not found
    public static final String RESOLVE = "Please remember to correct the dates between past vaccinations."; // Message to resolve vaccination date issues
    public static final String NOT_FOUND_ANIMAL = "Not found Animal ID"; // Error message for Animal not found
    public static final String NOT_FOUND_DOCTOR = "Not found Doctor ID"; // Error message for Doctor not found
    public static final String NOT_FOUND = "Not found object"; // General message for objects not found
    public static final String NULL_POINTER = "Please enter the ID values of dependent classes."; // Message for null pointers
    public static final String NULL_VALUES = "Please enter all requested data as JSON."; // Message for null values
    public static final String DATE_MISMATCH = "There is a vaccine that still provides protection."; // Message for date mismatch in vaccine entry
    public static final String UPDATE_NOT_FOUND_ID = "Enter the 'VALID ID' for 'UPDATE'"; // Message for update operation with invalid ID
    public static final String BAD_DATE = "Mismatched date entry"; // Message for invalid date entry
    public static final String NOT_UNIQUE = "Values must be unique"; // Message for non-unique values
    public static final String CREATED = "Saved"; // Message for successful object creation
    public static final String OK = "Ok"; // General success message
    public static final String DELETED = "Deleted"; // Message for successful deletion
    public static final String NOT_NULL_MESSAGE = "Variables not null "; // Message for non-null variables
    public static final String SAME_VALUES = "Saved data with the same values already exists."; // Message for duplicate values
    public static final String RESOURCE_NOT_FOUND = "INVALID URL"; // Message for invalid URL
    public static final String DAYS_CONFLICT = "The doctor is not working on this date."; // Message for doctor's availability conflict
    public static final String APPOINTMENT_CONFLICT = "Another appointment is available at the entered time."; // Message for appointment time conflict
    public static final String NOT_FOUND_APPOINTMENT = "There are no dates in the date ranges."; // Message for appointment not found
    public static final String EXISTING_APPOINTMENT = "Update and Delete cannot be done! An appointment has been made for this date. Contact with the customer.If you have provided the checks, go to ..../force-delete. "; // Message for existing appointment
    public static final String UNREADABLE = "There is a syntax error in the JSON request"; // Message for unreadable JSON request
    public static final String FORCE_UPDATE = "The animal to be changed has a checked vaccine with the same vaccine code. If you really want to make changes, do it via .../force-update. Attention: Control cannot be achieved between other vaccines!"; // Message for force update operation
    public static final String MISSING_PARAMETER = "Missing parameter entry.";
    public static final String UNAUTHORIZED = "Wrong Password or Username";
}

