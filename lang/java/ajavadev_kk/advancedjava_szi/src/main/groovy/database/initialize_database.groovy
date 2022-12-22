package database

import database.jdbc.Person
import groovy.sql.Sql

Sql db = Sql.newInstance(
        url: 'jdbc:mysql://127.0.0.1:3306/hr?sslMode=DISABLED', 
        driver: 'com.mysql.cj.jdbc.Driver',
        user: 'jpa', password: 'java')

db.execute "DROP TABLE IF EXISTS PEOPLE"
db.execute '''
    CREATE TABLE PEOPLE(
        id INT NOT NULL AUTO_INCREMENT,
        name VARCHAR(200) NOT NULL,
        PRIMARY KEY(id)
    )
'''

def people = [new Person(name: 'Picard'),
              new Person(name: 'Riker'),
              new Person(name: 'Troi'),
              new Person(name: 'Laforge'),
              new Person(name: 'Data'),
              new Person(name: 'Crusher')]

people.each { p ->
    println p
    db.executeInsert """
      insert into PEOPLE(name) values(${p.name})
    """
}

println db.rows("select id, name from hr.PEOPLE").collect { row ->
    new Person(id: row.id, name: row.name)
}

db.close()
