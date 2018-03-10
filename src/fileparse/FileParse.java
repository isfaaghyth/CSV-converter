package fileparse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isfaaghyth
 */
public class FileParse {
    
    private static List<String> temp = new ArrayList<>();

    public static void main(String[] args) {
        String member = "/Users/isfaaghyth/Desktop/colinadb/member.csv";
        if (readMemberCSV(member)) {
            for (String s: temp) {
                System.out.println(s);
            }
        }
    }
    
    private static Boolean readMemberCSV(String member) {
        try {
            FileInputStream fstream = new FileInputStream(member);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                int i = strLine.indexOf(',', 1 + strLine.indexOf(',', 1 + strLine.indexOf(',')));
                String firstPart = strLine.substring(0, i);
                String secondPart = strLine.substring(i+1);
                String memberId = getMemberId(strLine.split(",")[1]);
                temp.add(firstPart + "," + memberId + secondPart);
            }
            br.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    private static String getMemberId(String username) {
        String member = "/Users/isfaaghyth/Desktop/colinadb/member_id.csv";
        try {
            FileInputStream fstream = new FileInputStream(member);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine.contains(username)) {
                    return strLine.split(",")[1];
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }
    
}
