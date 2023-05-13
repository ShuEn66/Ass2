package my.edu.tarc.ass2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface databaseDao {

    class billBar(){
        var OverallUsage: Double = 0.0
        var BillingMonth: Int = 0

        constructor( OverallUsage: Double,  BillingMonth: Int) : this() {
            this.OverallUsage = OverallUsage
            this. BillingMonth=  BillingMonth
        }
    }

    //BILL
    @Query("SELECT OverallUsage FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getOverallUsage(accNo: Long, month: Int,  year:Int): Double

    @Query("SELECT BillStatus FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getBillStatus(accNo: Long, month: Int,  year:Int): String

    @Query("SELECT DueDate FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getPaymentDue(accNo: Long, month: Int,  year:Int):String

    @Query("SELECT TotalAmount FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getTotalAmount(accNo:Long, month: Int , year:Int): Double

    //@Query("SELECT * FROM Bill, ElectricityAcc WHERE ElectricityAcc.AccNUmber = Bill.AccNumber AND ElectricityAcc.AccNumber = :accNo ORDER BY ")
    //suspend fun getBillingHistory(accNo: Long ):LiveData<List<Bill>>

    @Query("SELECT CurrentCharges FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getCurrentCharges(accNo: Long,  month: Int,  year:Int): Double

    @Query("SELECT OutstandingCharges FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getOutstandingCharges(accNo: Long,  month: Int,  year:Int):Double

    @Query("SELECT InvoiceDate FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getInvoiceDate(accNo: Long,  month: Int,  year:Int): String

    @Query("SELECT OverdueCharges FROM Bill WHERE AccNumber = :accNo AND BillingMonth = :month AND BillingYear =  :year")
    suspend fun getOverdueCharges(accNo: Long,  month: Int,  year:Int):Double

    @Query("SELECT OverallUsage, BillingMonth FROM Bill WHERE AccNumber = :accNo")
    suspend fun getBarData(accNo: Long):List<billBar>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setBillingDetails(Bill:Bill)

    @Query("UPDATE Bill SET BillStatus = 'Successful' ")
    suspend fun updateBillStatus()


    //USER
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUserDetails(User:User)

    @Query("SELECT UserEmail FROM User WHERE AccNumber = :accNo")
    suspend fun getUserEmail(accNo: Long): String

    @Query("SELECT UserName FROM User WHERE UserEmail = :email")
    suspend fun getUserName(email: String): String

    @Query("SELECT UserIC FROM User WHERE UserEmail = :email")
    suspend fun getUserIC(email: String): String

    @Query("SELECT UserMobile FROM User WHERE UserEmail = :email")
    suspend fun getUserMobile(email: String): String





    //PAYMENT
    @Query("SELECT Payment.PaymentDate FROM Payment, Bill WHERE Payment.PaymentID = Bill.PaymentID AND Bill.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year ")
    suspend fun getPaymentDate(accNo: Long,  month: Int,  year:Int):String

    @Query("SELECT Payment.PaymentStatus FROM Payment, Bill  WHERE Payment.PaymentID = Bill.PaymentID AND Bill.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year ")
    suspend fun getPaymentStatus(accNo: Long,  month: Int, year:Int): String

    @Query("SELECT Payment.PaymentMethod FROM Payment, Bill WHERE Payment.PaymentID = Bill.PaymentID AND Bill.AccNumber = :accNo AND Bill.BillingMonth = :month AND Bill.BillingYear = :year ")
    suspend fun getPaymentMethod(accNo: Long,  month: Int,  year:Int): String

    @Query("UPDATE Payment SET PaymentStatus = 'Successful'")
    suspend fun updatePaymentStatus()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setPaymentDetails(Payment:Payment)

    //ELECTRICITYACC
    @Query("SELECT * FROM ElectricityAcc,User WHERE ElectricityAcc.AccNumber = User.AccNumber AND User.AccNumber =:accNo")
    fun getAccount(accNo: Long):LiveData<List<ElectricityAcc>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setAccDetails(ElectricityAcc:ElectricityAcc)





    //APPLIANCES (JIA XUAN PLS CHECK UR SQL I MIGHT WRONGGG)
    @Query("SELECT * FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail =:userEmail AND Appliances.AppliancesName= :appliancesName")
    fun getAppliances(userEmail: String, appliancesName: String ):LiveData<Appliances>

    @Query("SELECT Appliances.EstimatedUsage FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = :userEmail ")
    suspend fun getEstimatedUsage(userEmail: String ):Double

    @Query("SELECT Appliances.AppliancesName FROM Appliances, User WHERE Appliances.UserEmail = User.UserEmail AND User.UserEmail = :userEmail ")
    suspend fun getAppliancesName(userEmail: String ):String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setAppliances(Appliances:Appliances)

    @Delete
    suspend fun deleteAppliances(Appliances:Appliances)
}