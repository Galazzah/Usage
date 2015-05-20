package com.example.ghiyath.usage;


/**
 * Created by Ghiyath on 5/16/2015.
 */
public class Contact{
    //todo: text analytics
    private String contactID;
    private String name;
    //change phoneNum to Double
    private String phoneNum;
    private int smsSent;
    private int smsRecieved;

    public Contact (String phoneNum) {
        this.phoneNum = phoneNum;
    }
    //TODO: add name
    /*
    public Contact(String contactID/) {
        this.contactID = contactID;
        //this.name = name;
    }
    */

    //contactID
    public String getContactID() { return contactID; }

    //phoneNum
    //change phoneNum to Double
    public String getPhoneNum () { return phoneNum; }
    public void setPhoneNum ( String phoneNum ) { this.phoneNum = phoneNum; }

    //name
    public String getName() {
        return name;
    }

    //smsSent
    public int getSmsSent() {
        return smsSent;
    }
    public void setSmsSent(int smsSent) {
        this.smsSent = smsSent;
    }
    public void incrementSmsSent() {
        this.smsSent++;
    }

    //smsRecieved
    public int getSmsRecieved() {
        return smsRecieved;
    }
    public void setSmsRecieved(int smsRecieved){
        this.smsSent = smsSent;
    }
    public void incrementsmsRecieved() {
        this.smsRecieved++;
    }

    //Compare
    public boolean compareSMS(Contact c) { return this.smsRecieved+this.smsSent > c.smsRecieved+c.smsSent; }

    //TODO: additional fields
    /*
    private int callDuration;
    private int callsMade;
    private int callsReceived;

     */
    //Override toString to display phoneNum
    @Override
    public String toString() {
        return String.valueOf(getPhoneNum()) + String.valueOf(getSmsRecieved());
    }
}
