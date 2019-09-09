package com.pricecheck.model;

/**
 * Raw rate data fed into the PriceCheck system
 * @author Jake Sikora
 * @since 09/2019
 */
public class Rate {
  private String days;
  private String times;
  private String tz;
  private Long price;

  public String getDays() {
    return days;
  }

  public void setDays(String days) {
    this.days = days;
  }

  public String getTimes() {
    return times;
  }

  public void setTimes(String times) {
    this.times = times;
  }

  public String getTz() {
    return tz;
  }

  public void setTz(String tz) {
    this.tz = tz;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }
}

