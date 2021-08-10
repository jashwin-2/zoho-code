package model;

import java.util.ArrayList;
import java.util.List;

public class AccountModel
{
	static int id;
	private String accHolderName;
	private int accNo;
	protected int transactions;
	private long mobileNo;
	protected float accBalance;
	protected List<String> passbook=new ArrayList<>();
	protected float interest;
	private int pin;
	protected int transactionFee;
	protected String type;
	private boolean status;
	protected int transactionLimit;
	protected float afterLimitFee;
	

	public AccountModel(String name,long mobileNo,float balance,int pin)
	{
		status=true;
		this.transactions=0;
		this.accHolderName=name;
		this.mobileNo=mobileNo;
		this.accBalance=balance;
		passbook.add(balance + " initial deposite Current Balance : "+accBalance);
		this.accNo=++id;
		this.pin=pin;
		this.transactionFee=0;
	}

	public AccountModel(int accNo, String name, long mobileNo, float balance, int pin, List<String> passbook,Boolean status) 
	{
		this.accNo=accNo;
		this.accHolderName=name;
		this.mobileNo=mobileNo;
		this.accBalance=balance;
		this.pin=pin;
		this.passbook=passbook;
		this.status=status;
	}
	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public float getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(float accBalance) {
		this.accBalance = accBalance;
	}

	public List<String> getPassbook() {
		return passbook;
	}

	public void setPassbook(List<String> passbook) {
		this.passbook = passbook;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(int transactionFee) {
		this.transactionFee = transactionFee;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getAccNo() {
		return accNo;
	}

	public int getTransactions() {
		return transactions;
	}

	public String getType() {
		return type;
	}

	public void setTransactions(int transactions) {
		this.transactions = transactions;
	}

	public void addInpassBook(String string)
	{
		passbook.add(string);
	}
	public float getAfterLimitFee() {
		return afterLimitFee;
	}

	public void setAfterLimitFee(float afterLimitFee) {
		this.afterLimitFee = afterLimitFee;
	}

	public void setTransactionLimit(int transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public int getTransactionLimit() {
		return transactionLimit;
	}
}
