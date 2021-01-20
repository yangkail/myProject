package com.booking_fixed_table.model;

import java.util.*;

import com.booking_ing_table.model.Booking_Ing_TableVO;






public interface Booking_Fixed_TableDAO_interface {
	public void insert(Booking_Fixed_TableVO booking_fixed_tableVO);
    public void update(Booking_Fixed_TableVO booking_fixed_tableVO);
    public void delete(String rs_sieral);
    public Booking_Fixed_TableVO findByPrimaryKey(String rs_sieral);
    public List<Booking_Fixed_TableVO> getAll();
    public List<Booking_Fixed_TableVO> select_get_rs_id_all(String rs_id);
    public Booking_Fixed_TableVO findByRS_SEAT_SIERAL(String rs_seat_sieral);
   
    public void insertwithorder(Booking_Fixed_TableVO booking_fixed_tableVO, List<Booking_Ing_TableVO> booking_Ing_TableList);
  
}
