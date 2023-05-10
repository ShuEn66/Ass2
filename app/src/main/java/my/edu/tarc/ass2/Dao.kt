package my.edu.tarc.ass2

import androidx.lifecycle.LiveData
import androidx.room.*
import my.tarc.mycontact.*


@Dao
interface databaseDao {

    //BILL
    @Query("SELECT Bill.OverallUsage FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getOverallUsage(accNo: Int, month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.BillStatus FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getBillStatus(accNo: Int, month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.DueDate FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getPaymentDue(accNo: Int, month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.TotalAmount FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getTotalAmount(accNo: Int, month: Int , year:Int):LiveData<Bill>

    //@Query("SELECT * FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo ORDER BY ")
    //suspend fun getBillingHistory(accNo: Int ):LiveData<List<Bill>>

    @Query("SELECT  Bill.CurrentCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getCurrentCharges(accNo: Int,  month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.OutstandingCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getOutstandingCharges(accNo: Int,  month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.InvoiceDat FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getInvoiceDate(accNo: Int,  month: Int,  year:Int):LiveData<Bill>

    @Query("SELECT Bill.OverdueCharges FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getOverdueCharges(accNo: Int,  month: Int,  year:Int):LiveData<Bill>

    @Query("UPDATE Bill SET BillStatus = 'Successful' ")
    suspend fun updateBillStatus()

    //USER

    //PAYMENT
    @Query("SELECT Payment.PaymentDate FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year ")
    suspend fun getPaymentDate(accNo: Int,  month: Int,  year:Int):LiveData<Payment>

    @Query("SELECT Payment.PaymentStatus FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getPaymentStatus(accNo: Int,  month: Int, year:Int):LiveData<Payment>

    @Query("SELECT Payment.PaymentMethod FROM Payment, Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND Payment.PaymentID = Bill.PaymentID AND ElectricityAcc.AccNumber = :accNo OAND Bill.BillingMonth = :month AND Bill.BillingYear = :year")
    suspend fun getPaymentMethod(accNo: Int,  month: Int,  year:Int):LiveData<Payment>

    @Query("UPDATE Payment SET PaymentStatus = 'Successful'")
    suspend fun updatePaymentStatus()

    //ELECTRICITYACC
    @Query("SELECT * FROM ElectricityAcc WHERE AccNumber = : accNo")
    suspend fun getAccount(accNo: Int):LiveData<ElectricityAcc>

    //APPLIANCES
    //get appliance details
    @Query("SELECT * FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = : userEmail AND Appliances.AppliancesID= : appliancesID")
    suspend fun getAppliancesName(userEmail: String, appliancesID: Int ):LiveData<Appliances>

    //get the list of appliances
    @Query("SELECT Appliance.AppliancesType, Appliance.EstimatedUsage FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = : userEmail ")
    suspend fun getEstimatedUsage(userEmail: String ):LiveData<List<Appliances>>

    //@Delete
    //suspend fun delete(contact: Contact)
}