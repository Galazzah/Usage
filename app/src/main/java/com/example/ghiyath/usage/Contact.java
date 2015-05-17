package com.example.ghiyath.usage;

/**
 * Created by Ghiyath on 5/16/2015.
 */
public class Contact {
    //todo: text analytics
    private String contactID;
    private String name;
    private int smsSent;
    private int smsRecieved;

    //TODO: add name
    public Contact(String contactID/*, String name*/) {
        this.contactID = contactID;
        //this.name = name;
    }

    //contactID
    public String getContactID() {
        return contactID;
    }

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

    //TODO: additional fields
    /*
    private int callDuration;
    private int callsMade;
    private int callsReceived;

     */

}
