package com.example.aspectjdemo;  
  
  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/** 
 * Created by wuzulong on 2017/6/30.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface DebugTrace {}