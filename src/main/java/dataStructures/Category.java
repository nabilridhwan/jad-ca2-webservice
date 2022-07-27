/*
 * 	Name: Xavier Tay Cher Yew
	Admin No: P2129512
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI  
 * */

package dataStructures;

import java.sql.ResultSet;

public class Category {
    int category_id;
    String category_name;
    String desc;
    String image;
    int count;

    public Category(ResultSet rs) {
        try {
            category_id = rs.getInt(1);
            category_name = rs.getString(2);
            desc = rs.getString(3);
            image = rs.getString(4);
            count = rs.getInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }
}



