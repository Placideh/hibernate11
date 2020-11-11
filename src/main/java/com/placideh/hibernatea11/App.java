package com.placideh.hibernatea11;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Configuration con=new Configuration().configure().addAnnotatedClass(Student.class);
    	SessionFactory sf=con.buildSessionFactory();
    	Session session=sf.openSession();
    	session.beginTransaction();
    	
		/*
		 * THIS IS FOR GETTING DATA FROM THE DATABASE BY JUST USING NATIVE QUERY OR SQL
		 * SQLQuery query=session.createSQLQuery("select * from student");
		 * List<Object[]> students=(List<Object[]>)query.list(); for(Object[]
		 * student:students) {
		 * System.out.println(student[0]+":"+student[1]+":"+student[2]); }
		 */
    	
		/*
		 * //THIS IS ALSO WORKS THE SAME AS THE ABOVE WAY BUT IT IS SOMEHOW CLEANER
		 * //THIS WORKS WHEN WE WANT TO GET THE ENTIRE RAWS SQLQuery
		 * query=session.createSQLQuery("select * from student");
		 * query.addEntity(Student.class); List<Student> students=query.list();
		 * for(Student student:students) { System.out.println(student); }
		 */
    	
		SQLQuery query=session.createSQLQuery("select name,marks from student where marks>70");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		  List students=query.list(); 
		  
		  for(Object student:students) {
			  Map m=(Map) student;
		  System.out.println(m.get("name")+":"+m.get("marks"));
		  }
    	
    	session.getTransaction().commit();
    	session.close();
    }
}
