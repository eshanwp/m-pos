/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import java.util.Scanner;
import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/**
 *
 * @author esh
 */
public class NewClass {

    public static void main(String[] args) {

//        Scanner s = new Scanner(System.in);
//        String str = s.nextLine();
//
//        String first = str.substring(0, str.indexOf('+'));
//        String second = str.substring(str.indexOf('+') + 1, str.lastIndexOf('+'));
//        String third = str.substring(str.lastIndexOf('+') + 1, str.length());
//
//        System.out.println(first);
//        System.out.println(second);
//        System.out.println(third);
//        String str = "30%";
//        System.out.println("last char = " + str.charAt(str.length() - 1));
        StringBuilder s = new StringBuilder("250%");
        System.out.println(s.deleteCharAt(s.lastIndexOf("%")));
        

    }

}
