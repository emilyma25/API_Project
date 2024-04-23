import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

//make mafia with 10 cats, randomly assign them roles and make chatgpt create a story and randomly kill one
//use chatgpt to create a story to how the game went like mafia

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


// Program for print data in JSON format.
public class ReadJson implements ActionListener{

    private JFrame mf;
    private JLabel dogScoreLabel;
    JLabel catpicLabel;
    JLabel dogpicLabel;
    private int  newcatheight;
    private int  newcatwidth;
    private int newdogheight;
    private int newdogwidth;
//    private JTextArea catScore;
//    private JTextArea dogScore;
    private JLabel catScore;
    private JLabel dogScore;
    private JLabel catScoreLabel;
    private JPanel choicePanel;
    private JPanel catPic;

    private JPanel dogPic;

    private JLabel dogChoice;
    private JLabel catChoice;
    private JTextArea winner;
    private JLabel scoreLabel;
    private JPanel catPanel;
    private JPanel dogPanel;
    private JPanel middlePanel;
    private JPanel dogScorePanel;
    private JPanel catScorePanel;
    private JPanel miniScorePanel;
    private JPanel fullScorePanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JLabel catCharacter;
    private JLabel dogCharacter;
    private int WIDTH = 800;
    private int HEIGHT = 700;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    public int catsScore = 0;
    public int dogsScore = 0;
    private int index = 0;

    //need buttons to pick cat, pick dog, and play

    public static void main(String args[]) throws ParseException{
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
//        ReadJson readjson = new ReadJson();
//        readjson.addImage();

//        file.put("Full Name", "Ritu Sharma");
//        file.put("Roll No.", new Integer(1704310046));
//        file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        pull();
        ReadJson read = new ReadJson();

    }




    public ReadJson() {
        prepareGUI();
        showEventDemo();

    }

    public static void pull() throws ParseException {
        String catoutput = "abc";
        String cattotlaJson="";
        try {
            URL url = new URL("https://api.thecatapi.com/v1/images/search?limit=10");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((catoutput = br.readLine()) != null) {
                System.out.println(catoutput);
                cattotlaJson+=catoutput;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser catparser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONArray catjsonObjectArray = (org.json.simple.JSONArray) catparser.parse(cattotlaJson);
        System.out.println(catjsonObjectArray);

        try {
            JSONObject cat = (JSONObject) catjsonObjectArray.get(0);
            String url = (String)cat.get("url");

            System.out.println(catjsonObjectArray.get(0));
            JSONObject newCat = (JSONObject) catjsonObjectArray.get(0);
            String catUrl = (String) newCat.get("url");
            System.out.println(catUrl);

//            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonARRAY.get("url");
//            int n =   msg.size(); //(msg).length();
//            for (int i = 0; i < n; ++i) {
//                String test =(String) msg.get(i);
//                System.out.println(test);
//                // System.out.println(person.getInt("key"));
//            }
            //String name= (String)jsonObject.get("height");
            System.out.println(url);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        //dog api
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://random.dog/woof.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {
            String dog = (String) jsonObject.get("url");
            System.out.println(dog);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void prepareGUI(){

        mf = new JFrame("Java SWING Examples");
        mf.setSize(WIDTH, HEIGHT);
        mf.setLayout(new GridLayout(2,1));
        mf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3));

        catPanel = new JPanel();
        catPanel.setLayout(new BorderLayout());
        //add place for image
        catPic = new JPanel();

        try {
//            BufferedImage catmyPicture = ImageIO.read(new File("cat pic api.png"));
//            ImageIcon catimageIcon = new ImageIcon(new ImageIcon("cat pic api.png").getImage().getScaledInstance(220, 300, Image.SCALE_DEFAULT));

            //new
            //URL url = new URL("https://cdn2.thecatapi.com/images/MTgzMzAyOA.jpg");
            URL url = new URL("https://cdn2.thecatapi.com/images/8f6.jpg");
            BufferedImage ErrorImage = ImageIO.read(new File("cat pic api.png"));
            BufferedImage inputImageBuff = ImageIO.read(url.openStream());

            ImageIcon inputImage;
            if (inputImageBuff != null) {
                inputImage = new ImageIcon(inputImageBuff.getScaledInstance(300, 200, Image.SCALE_SMOOTH));
                // = new JLabel();
                if (inputImage != null) {
                    catpicLabel = new JLabel(inputImage);
                } else {

                    catpicLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));

                }
                catPic.removeAll();
                catPic.add(catpicLabel);
                //mainFrame.add(imagePanel, BorderLayout.CENTER);

            }
            else{
                catpicLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));

            }


            //new
//            catpicLabel.setIcon(catimageIcon);
//            catPic.add(catpicLabel);
        }
        catch (IOException e){
            System.out.println(e);
        }

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
//        catcanvas = new Canvas();
//        catcanvas.setBounds(0, 0, WIDTH, HEIGHT);
//        catcanvas.setIgnoreRepaint(true);
//        catimage = Toolkit.getDefaultToolkit().getImage("cat pic api.png");
//
//        catPic.add(catcanvas);  // adds the canvas to the panel.

        // sets up things so the screen displays images nicely.
//        catcanvas.createBufferStrategy(2);
//        bufferStrategy = catcanvas.getBufferStrategy();
//        catcanvas.requestFocus();
//        System.out.println("DONE cat graphic setup");
//        catCharacter = new JLabel("cat pic");
//        catCharacter.setHorizontalAlignment(JLabel.CENTER);
        catPanel.add(catPic, BorderLayout.CENTER);
        catPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //needs button

        dogPanel = new JPanel();
        dogPanel.setLayout(new BorderLayout());
        dogPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //add place for image
        dogPic = new JPanel();


        //dogimage = Toolkit.getDefaultToolkit().getImage("dog pic api.png");

//        dogCharacter = new JLabel("dog pic");
//        dogCharacter.setHorizontalAlignment(JLabel.CENTER);
        try {
            BufferedImage dogmyPicture = ImageIO.read(new File("dog pic api.png"));
            ImageIcon dogimageIcon = new ImageIcon(new ImageIcon("dog pic api.png").getImage().getScaledInstance(300, 175, Image.SCALE_DEFAULT));

            JLabel dogpicLabel = new JLabel(dogimageIcon);
            dogPic.add(dogpicLabel);

        }
        catch (IOException e){
            System.out.println(e);
        }


//        dogPic.add(dogcanvas);  // adds the canvas to the panel.
//
//        // sets up things so the screen displays images nicely.
//        dogcanvas.createBufferStrategy(2);
//        bufferStrategy = dogcanvas.getBufferStrategy();
//        dogcanvas.requestFocus();
//        System.out.println("DONE dog graphic setup");

        dogPanel.add(dogPic, BorderLayout.CENTER);
        //needs button

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(2, 1));
        choicePanel = new JPanel();
        choicePanel.setLayout(new GridLayout(1, 2));
        catChoice = new JLabel(" ");
        catChoice.setFont(new Font("Serif", Font.PLAIN, 17));
        catChoice.setHorizontalAlignment(JLabel.CENTER);
        catChoice.setBorder(BorderFactory.createLineBorder(Color.black));
        dogChoice = new JLabel(" ");
        dogChoice.setFont(new Font("Serif", Font.PLAIN, 17));
        dogChoice.setHorizontalAlignment(JLabel.CENTER);
        dogChoice.setBorder(BorderFactory.createLineBorder(Color.black));
        choicePanel.add(catChoice);
        choicePanel.add(dogChoice);
        choicePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        winner = new JTextArea();
        winner.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        winner.setText("winner");
        middlePanel.add(choicePanel);
        middlePanel.add(winner);

        topPanel.add(catPanel);
        topPanel.add(middlePanel);
        topPanel.add(dogPanel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        //end menu at top

        mf.add(mb);  //add menu bar
        mf.setJMenuBar(mb); //set menu bar

        fullScorePanel = new JPanel();
        fullScorePanel.setLayout(new BorderLayout());
        //add go button
        miniScorePanel = new JPanel();
        miniScorePanel.setLayout(new GridLayout(1, 2));
        scoreLabel = new JLabel("score:");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        fullScorePanel.add(scoreLabel, BorderLayout.NORTH);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 15));

        //cat score
        catScorePanel = new JPanel();
        catScorePanel.setLayout(new GridLayout(2, 1));
        catScoreLabel = new JLabel("cat score:");
        catScoreLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        catScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        catScoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        catScore = new JLabel("0");
        catScore.setHorizontalAlignment(JLabel.CENTER);
        catScore.setFont(new Font("Serif", Font.PLAIN, 30));

        //catScore.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        catScore.setText(Integer.toString(catsScore));
        catScore.setBorder(BorderFactory.createLineBorder(Color.black));
        catScorePanel.add(catScoreLabel);
        catScorePanel.add(catScore);
        catScorePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //dog score
        dogScorePanel = new JPanel();
        dogScorePanel.setLayout(new GridLayout(2, 1));
        dogScoreLabel = new JLabel("dog score:");
        dogScoreLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        dogScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        dogScoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        dogScore = new JLabel("0");
        dogScore.setHorizontalAlignment(JLabel.CENTER);
        dogScore.setFont(new Font("Serif", Font.PLAIN, 30));
        dogScore.setBorder(BorderFactory.createLineBorder(Color.black));
        //dogScore.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        dogScore.setText(Integer.toString(dogsScore));
        dogScorePanel.add(dogScoreLabel);
        dogScorePanel.add(dogScore);
        dogScorePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        miniScorePanel.add(catScorePanel);
        miniScorePanel.add(dogScorePanel);
        fullScorePanel.add(miniScorePanel, BorderLayout.CENTER);
        bottomPanel.add(fullScorePanel, BorderLayout.CENTER);

        mf.add(topPanel);
        mf.add(bottomPanel);

        mf.setVisible(true);
    }



    private void showEventDemo() {

        JButton chooseCatChar = new JButton("new cat");
        JButton chooseDogChar = new JButton("new dog");
        JButton go = new JButton("go");

        catPanel.add(chooseCatChar, BorderLayout.SOUTH);
        dogPanel.add(chooseDogChar, BorderLayout.SOUTH);
        bottomPanel.add(go, BorderLayout.NORTH);

        mf.setVisible(true);
        chooseCatChar.setActionCommand("new cat");
        chooseCatChar.addActionListener(new ButtonClickListener());
        chooseDogChar.setActionCommand("new dog");
        chooseDogChar.addActionListener(new ButtonClickListener());
        go.setActionCommand("go");
        go.addActionListener(new ButtonClickListener());
        mf.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            winner.cut();
        if (e.getSource() == paste)
            winner.paste();
        if (e.getSource() == copy)
            winner.copy();
        if (e.getSource() == selectAll)
            winner.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals("new cat")) {
                System.out.println("hi");
                String output = "abc";
                String totlaJson = "";
                boolean g = true;

                try {

                    URL url = new URL("https://api.thecatapi.com/v1/images/search?limit=10");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    }
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                        totlaJson += output;
                    }

                    conn.disconnect();

                } catch (MalformedURLException ea) {
                    ea.printStackTrace();

                } catch (IOException i) {
                    i.printStackTrace();
                }

                JSONParser parser = new JSONParser();
                //System.out.println(str);
                try {
                    org.json.simple.JSONArray jsonObjectArray = (org.json.simple.JSONArray) parser.parse(totlaJson);
//                System.out.println(jsonObjectArray);

                    System.out.println(jsonObjectArray.size());
                    System.out.println(jsonObjectArray.get(0));
                    JSONObject newCat = (JSONObject) jsonObjectArray.get(0);
                    String catUrl = (String) newCat.get("url");

//                    int width = bimg.getWidth();
//                    int height = bimg.getHeight();
//                    System.out.println(catUrl);
//                    System.out.println("height: " + catHeight);
//                    System.out.println("width: " + catWidth);
//                    int HWratio = catHeight/catWidth;
//                    int WHratio = catWidth/catHeight;
//                    if(catHeight>catWidth){
//                        newcatheight = 500;
//                        newcatwidth = 500*WHratio;
//                    }else{
//                        newcatwidth = 300;
//                        newcatheight = 300*HWratio;
//                    }

//                    BufferedImage img = ImageIO.read(new URL(catUrl));
//                    catpicLabel.setIcon(new javax.swing.ImageIcon(img));

                    //catCharacter.setText(catUrl);

                    //new
                    URL url = new URL(catUrl);
                    BufferedImage ErrorImage = ImageIO.read(new File("cat pic api.png"));
                    BufferedImage inputImageBuff = ImageIO.read(url.openStream());

                    ImageIcon inputImage;
                    if (inputImageBuff != null) {
                        double width = inputImageBuff.getWidth();
                        double height = inputImageBuff.getHeight();
                        System.out.println(catUrl);
//                        System.out.println("height: " + height);
//                        System.out.println("width: " + width);
                        double HWratio = height/width;
                        double WHratio = width/height;
//                        System.out.println(HWratio);
//                        System.out.println(WHratio);

                        if(height>width){
                            newcatheight = 400;
                            newcatwidth = (int)(400*WHratio);
                        }else{
                            newcatwidth = 260;
                            newcatheight = (int)(260*HWratio);
                        }
                        inputImage = new ImageIcon(inputImageBuff.getScaledInstance(newcatwidth, newcatheight, Image.SCALE_SMOOTH));
                        // = new JLabel();
                        if (inputImage != null) {
                            catpicLabel = new JLabel(inputImage);
                        } else {

                            catpicLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));

                        }
                        catPic.removeAll();
                        catPic.repaint();
                        catPic.add(catpicLabel);
                        //mainFrame.add(imagePanel, BorderLayout.CENTER);

                    }
                    else{
                        catpicLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));

                    }

                } catch (IOException u) {
                    System.out.println(u);
                    System.out.println("sadness");
                    BufferedImage ErrorImage = null;
                    try {
                        ErrorImage = ImageIO.read(new File("cat pic api.png"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JLabel imageLabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));

                    catPic.removeAll();
                    catPic.add(imageLabel);
                    //mainFrame.add(imagePanel);

                }
                 catch (ParseException ea) {
                    ea.printStackTrace();
                }
//                catch (IOException a) {
//                    a.printStackTrace();
//
//                }

            }


            //dog api
            if(command.equals("new dog")) {
                String dogoutput = "abc";
                String dogtotlaJson = "";
                try {

                    URL url = new URL("https://random.dog/woof.json");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");

                    if (conn.getResponseCode() != 200) {

                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));


                    System.out.println("Output from Server .... \n");
                    while ((dogoutput = br.readLine()) != null) {
                        System.out.println(dogoutput);
                        dogtotlaJson += dogoutput;
                    }

                    conn.disconnect();

                } catch (MalformedURLException a) {
                    a.printStackTrace();

                } catch (IOException a) {
                    a.printStackTrace();
                }

                JSONParser dogparser = new JSONParser();

                try {
                    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) dogparser.parse(dogtotlaJson);
                    //System.out.println(jsonObject);

                    String dog = (String) jsonObject.get("url");
                    while(dog.contains(".mp4")||dog.contains(".gif")||dog.contains("webm")){
                        String dogoutput2 = "abc";
                        String dogtotlaJson2 = "";
                        try {

                            URL url = new URL("https://random.dog/woof.json");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.setRequestProperty("Accept", "application/json");

                            if (conn.getResponseCode() != 200) {

                                throw new RuntimeException("Failed : HTTP error code : "
                                        + conn.getResponseCode());
                            }

                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    (conn.getInputStream())));


                            System.out.println("Output from Server .... \n");
                            while ((dogoutput2 = br.readLine()) != null) {
                                System.out.println(dogoutput2);
                                dogtotlaJson2 += dogoutput2;
                            }

                            conn.disconnect();

                        } catch (MalformedURLException a) {
                            a.printStackTrace();

                        } catch (IOException a) {
                            a.printStackTrace();
                        }

                        JSONParser dogparser2 = new JSONParser();

                        try {
                            org.json.simple.JSONObject jsonObject2 = (org.json.simple.JSONObject) dogparser2.parse(dogtotlaJson2);
                            //System.out.println(jsonObject);

                            dog = (String) jsonObject2.get("url");


                    }catch (Exception a) {
                            a.printStackTrace();
                        }
                    }
                    System.out.println(dog);

                    //dogCharacter.setText(dog);

                    //new

                    URL url = new URL(dog);

                    BufferedImage ErrorImage = ImageIO.read(new File("dog pic api.png"));
                    BufferedImage inputImageBuff = ImageIO.read(url.openStream());


                    ImageIcon inputImage;
                    if (inputImageBuff != null) {
                        double width = inputImageBuff.getWidth();
                        double height = inputImageBuff.getHeight();
//                        System.out.println("height: " + height);
//                        System.out.println("width: " + width);
                        double HWratio = height/width;
                        double WHratio = width/height;
//                        System.out.println(HWratio);
//                        System.out.println(WHratio);
                        if(height>width){
                            newdogheight = 400;
                            newdogwidth = (int)(400*WHratio);
                            //System.out.println(newdogwidth);
                        }else{
                            newdogwidth = 260;
                            newdogheight = (int)(260*HWratio);
                            //System.out.println(newdogheight);
                        }
                        inputImage = new ImageIcon(inputImageBuff.getScaledInstance(newdogwidth, newdogheight, Image.SCALE_SMOOTH));
                        // = new JLabel();
                        if (inputImage != null) {
                            dogpicLabel = new JLabel(inputImage);
                        } else {

                            dogpicLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));

                        }
                        dogPic.removeAll();
                        dogPic.repaint();
                        dogPic.add(dogpicLabel);
                        //mainFrame.add(imagePanel, BorderLayout.CENTER);

                    }
                    else{
                        dogpicLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));

                    }

                    //end new


                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
            if(command.equals("go")){

                int intCatChoice = (int)(Math.random()*3)+1;
                int intDogChoice = (int)(Math.random()*3)+1;
                System.out.println(intCatChoice);
                System.out.println(intDogChoice);
                if(intCatChoice==1) {
                    catChoice.setText("rock");
                } else if (intCatChoice==2) {
                    catChoice.setText("paper");
                }else{
                    catChoice.setText("scissors");
                }
                if(intDogChoice==1) {
                    dogChoice.setText("rock");
                } else if (intDogChoice==2) {
                    dogChoice.setText("paper");
                }else{
                    dogChoice.setText("scissors");
                }

                //1 rocks
                //2 paper
                // 3 sciceisorws

                if(intCatChoice==intDogChoice){
                    winner.setText("it's a tie!");
                } else if (intCatChoice==1 && intDogChoice==2){
                    winner.setText("dogs win!");
                    dogsScore+=1;
                } else if (intCatChoice==1 && intDogChoice==3) {
                    winner.setText("cats win!");
                    catsScore+=1;
                } else if (intCatChoice==2 && intDogChoice==1) {
                    winner.setText("cats win!");
                    catsScore+=1;
                } else if (intCatChoice==2 && intDogChoice==3) {
                    System.out.println(" cat paper dog sci");
                    winner.setText("dogs win!");
                    dogsScore+=1;
                } else if (intCatChoice==3 && intDogChoice==1) {
                    winner.setText("dogs win!");
                    dogsScore+=1;
                } else{
                    //(intCatChoice==3 && intDogChoice==2)
                    System.out.println("cat sci 3 dog 2 paper");
                    winner.setText("cats win!");
                    catsScore+=1;
                }
                catScore.setText(Integer.toString(catsScore));
                dogScore.setText(Integer.toString(dogsScore));

            }

            mf.setVisible(true);
        }
    }
}


