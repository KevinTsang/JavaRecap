package com.tedneward.example;

import java.beans.*;
import java.lang.Comparable;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn = "";
  private boolean propertyChangeFired = false;
  private static int instances;
  
  public Person() {
    this("", 0, 0.0);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    instances++;
  }

  public static int count() {
    return instances;
  }

  public static List<Person> getNewardFamily() {
    List<Person> people = new ArrayList<Person>();
    people.add(new Person("Charlotte", 43, 150000));
    people.add(new Person("Matthew", 15, 0));
    people.add(new Person("Michael", 22, 10000));
    people.add(new Person("Ted", 41, 250000));
    return people;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int value) {
    if (value < 0)
      throw new IllegalArgumentException();
    int old = age;
    age = value;
    this.pcs.firePropertyChange("age", old, value);
    propertyChangeFired = true;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    if (value == null)
      throw new IllegalArgumentException();
    String old = name;
    name = value;
    this.pcs.firePropertyChange("name", old, value);

  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double value) {
    if (salary < 0)
      throw new IllegalArgumentException();
    double old = salary;
    salary = value;
    this.pcs.firePropertyChange("salary", old, value);
    propertyChangeFired = true;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return this.age == p.getAge() && this.name == p.getName();
    }
    return false;
  }

  @Override
  public int compareTo(Person other) {
    return -1 * (int)(this.salary - other.getSalary());
  }

  @Override
  public String toString() {
    return "[Person name:" + name +  " age:" + age + " salary:" + salary + "]";
  }


  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
