package com.gtunes

class User {
    
    Date dateCreated
    Date lastUpdated

    String login
    String password
    String firstName
    String lastName
    String email
    static hasMany = [purchasedSongs:Song]
    
    static constraints = {
        login blank:false, nullable:false,size:5..15,matches:/[\S]+/, unique:true
        password blank:false,nullable:false, size:5..15,matches:/[\S]+/
        firstName blank:false, nullable:false
        lastName blank:false, nullable:false
        email email: true, blank: false, unique: true
    }
}
