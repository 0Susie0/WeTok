package com.example.wetok.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.example.wetok.bean.Post;
import com.example.wetok.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * The DataGenerator class is used to generate all the users and posts data
 * @author Xinyue Hu
 */
public class DataGenerator {

    public DataGenerator() throws Exception {
    }

    /**
     * Read data from raw data files
     * @param path
     * @return list
     * @throws Exception
     */
    public static List<String> readFromFile(String path) throws Exception {
        FileReader fileReader =new FileReader(path);
        BufferedReader bufferedReader =new BufferedReader(fileReader);
        String line;
        List<String> list =new ArrayList<String>();

        while((line=bufferedReader.readLine())!=null) {
            if(line.trim().length()>2) {
                list.add(line);
            }
        }
        bufferedReader.close();
        fileReader.close();
        return list;
    }

    /**
     * Generate password
     * @return the password
     */
    public static String Password(){
        final int maxNum = 36;
        int i; //generate random number
        int count = 0; //count the length of the password
        StringBuilder pwd = new StringBuilder("");
        Random r = new Random();
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9' };

        while (count < 6) {
            i = Math.abs(r.nextInt(maxNum));
            pwd.append(str[i]);
            count++;
        }

        return pwd.toString();
    }

    /**
     * Read user information from corresponding files
     * @param size
     * @return user information
     */
    public static Map<String,String> generateUserInfo(int size){
        Map<String,String> userinfo = new HashMap<>();
        try {
            List<String> firstName =  readFromFile("src/FirstName.txt");
            List<String> lastName = readFromFile("src/LastName.txt");
            List<String> gender = readFromFile("src/Gender.txt");
            userinfo = generateNameSex(firstName, lastName,gender,size);
            return userinfo;
        } catch (Exception e) {
            System.out.println("can't read file!");
        }
       return userinfo;
    }

    /**
     * Generate user's name and sex
     * @param firstName
     * @param lastName
     * @param gender
     * @param size
     * @return name and sex
     * @throws Exception
     */
    public static Map<String,String> generateNameSex(List<String> firstName, List<String> lastName, List<String> gender, int size) throws Exception {
            Map<String,String> info = new HashMap<>();
            for(int i=0;i<size;i++) {
                int findex = (int) (Math.random() * firstName.size());
                int lindex = (int) (Math.random() * lastName.size());
                String name = firstName.get(findex) + lastName.get(lindex);
                String sex = gender.get(findex);
                info.put(name, sex);
            }
            return info;
    }

    /**
     * Generate user age
     * @return age
     */
    public static int generateAge(){
        return (int)(Math.random()*55)+18;
    }

    /**
     * generate posts
     * @param id
     * @param name
     * @param u_img
     * @param statu
     * @return post list
     */
    public static List<Post> generatePosts(String id,String name,String u_img,List<String> statu){
        List<Post> pos = new ArrayList<>();
        String[] tags = {"#state","#mood","#scenery","#weekend","#time","#trip","#plan"};
            for (int i = 0; i < 3; i++) {
                //Comments
                String comment = "";
                //likes
                int like = (int)(Math.random()*10)+1;
                //reposts
                int repost = 0;
                //content
                int index = (int)(Math.random()*statu.size());
                //tags
                List<String> list = new ArrayList<>();
                String time = timeGenerate();
                String str = statu.get(index);
                String[] split = str.split("#");
                String content = split[0];
                String tag = "#"+split[1];
                list.add(tag);

                if(i==2){
                    String sb = tags[(int)(Math.random()*7)];
                    while(sb.equals(tag)){
                        int j = (int)(Math.random()*7);
                        sb = tags[j];
                    }
                    list.add(sb);
                }

                //img
                String imgPath = "";
                //Post currentpost = new Post(id, name, u_img, time, list, comment,like,repost,content,imgPath);
                //pos.add(currentpost);
            }
        return pos;
    }

    /**
     * Generate the post publishing time
     * @return String
     */
    public static String timeGenerate() {
        int hour=(int)(Math.random()*23)+1;
        int minute= (int)(Math.random()*6);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int date = ThreadLocalRandom.current().nextInt(1, 29);
        return month + "." + date + " " + hour + ":" + minute + "0";
    }

    /**
     * Generate Address
     * @return address
     */
    public static String generateAdress(){
        String[] location = {"Sydney","Melbourne","Brisbane",
                "Perth","Adelaide","Canberra","Broome","Hobart",
                "Darwin","Cairns"};
        Random r = new Random();
        int i = Math.abs(r.nextInt(10));
        //可以调用google map api
        return location[i];
    }

    /**
     * Generate user email
     * @return email
     */
    public static String generateEmail(){
        String[] suffix = new String[]{"@qq.com", "@gmail.com", "@163.com", "@126.com",
                "@yahoo.com","sina.com","hotmail.com"};
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<9;i++){
            int j = (int)(Math.random()*9)+1;
            sb.append(j);
        }
        int index = (int)(Math.random()*suffix.length);
        sb.append(suffix[index]);
        return sb.toString();
    }

    /**
     * Generate user phone number
     * @return phone number
     */
    public static String generatePhone(){
        String first = "4";
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        while(count<8){
            int i = (int)(Math.random()*9)+1;
            sb.append(i);
            count++;
        }
        return sb.toString();
    }

    /**
     * Create Json file
     * @throws Exception
     */
    public static void createJsonFile() throws Exception {
        File file = new File("src/infoResource.json");
        file.delete();//Ensure the file is unique
        ArrayList<User> userlist = new ArrayList<>();
        Map<String,String> userInfo = generateUserInfo(1100);
        List<String> statu = readFromFile("src/Post.txt");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //add a object to Json
        int i = 0;
        for(Map.Entry<String,String> entry : userInfo.entrySet()){
            //ID
            String id = String.valueOf(i++);
            //name
            String name = entry.getKey();
            //sex
            String sex = entry.getValue();
            //age
            int age = generateAge();
            //password
            String psd = Password();
            //Follower
            List<User> followers = null;
            //Subscriber
            List<User> subscribers = null;
            //Address
            String address = generateAdress();
            //Email
            String email = generateEmail();
            //phone
            String phone = generatePhone();
            //image
            String imgPath = "default";
            //Posts
            List<Post> posts = generatePosts(id,name,imgPath,statu);
            //write into a User object
            User currentUser = new User(id, name, psd,sex,age,followers, subscribers, posts, address,email,phone,imgPath);
            userlist.add(currentUser);
        }
        try(FileWriter fileWriter = new FileWriter(file)){
            gson.toJson(userlist,fileWriter);
            System.out.println("Successfully create json file!");
        }catch(Exception e){
            System.out.println("cannot write json file!");
        }
    }



}
