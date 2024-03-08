import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

//make mafia with 10 cats, randomly assign them roles and make chatgpt create a story and randomly kill one
//use chatgpt to create a story to how the game went like mafia

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

// Program for print data in JSON format.
public class ReadJson implements ActionListener{

    private JFrame mf;
    private JLabel dogScoreLabel;
    private JTextArea catScore;
    private JTextArea dogScore;
    private JLabel catScoreLabel;
    private JPanel choicePanel;
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
    public int rock = 1;
    public int paper = 2;
    public int scissors = 3;

    //need buttons to pick cat, pick dog, and play

    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));


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
        String output = "abc";
        String totlaJson="";
        try {
//https://api.thecatapi.com/v1/images/search?limit=10
//https://random.dog/woof.json
            URL url = new URL("https://api.thecatapi.com/v1/images/search?limit=10");
            //each link is part of an object in the array, go through the indexes and substring the url
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
        //System.out.println(str);
        org.json.simple.JSONArray jsonARRAY = (org.json.simple.JSONArray) parser.parse(totlaJson);
        System.out.println(jsonARRAY);

        try {
            JSONObject cat = (JSONObject) jsonARRAY.get(0);
            String url = (String)cat.get("url");

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
        catCharacter = new JLabel("cat");
        catCharacter.setHorizontalAlignment(JLabel.CENTER);
        catPanel.add(catCharacter, BorderLayout.CENTER);
        //needs button

        dogPanel = new JPanel();
        dogPanel.setLayout(new BorderLayout());
        //add place for image
        dogCharacter = new JLabel("dog");
        dogCharacter.setHorizontalAlignment(JLabel.CENTER);
        dogPanel.add(dogCharacter, BorderLayout.CENTER);
        //needs button

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(2, 1));
        choicePanel = new JPanel();
        choicePanel.setLayout(new GridLayout(1, 2));
        catChoice = new JLabel("scissors");
        catChoice.setHorizontalAlignment(JLabel.CENTER);
        dogChoice = new JLabel("paper");
        dogChoice.setHorizontalAlignment(JLabel.CENTER);
        choicePanel.add(catChoice);
        choicePanel.add(dogChoice);
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
        fullScorePanel.add(scoreLabel);
        //cat score
        catScorePanel = new JPanel();
        catScorePanel.setLayout(new GridLayout(2, 1));
        catScoreLabel = new JLabel("cat score");
        catScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        catScore = new JTextArea();
        catScore.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        catScore.setText("2");
        catScorePanel.add(catScoreLabel);
        catScorePanel.add(catScore);
        //dog score
        dogScorePanel = new JPanel();
        dogScorePanel.setLayout(new GridLayout(2, 1));
        dogScoreLabel = new JLabel("dog score");
        dogScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        dogScore = new JTextArea();
        dogScore.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        dogScore.setText("1");
        dogScorePanel.add(dogScoreLabel);
        dogScorePanel.add(dogScore);
        miniScorePanel.add(catScoreLabel);
        miniScorePanel.add(dogScoreLabel);
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
            catScore.cut();
        if (e.getSource() == paste)
            catScore.paste();
        if (e.getSource() == copy)
            catScore.copy();
        if (e.getSource() == selectAll)
            catScore.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("hi");

            String command = e.getActionCommand();
            if(command.equals("go")){

                int intCatChoice = (int)Math.random()*3+1;
                int intDogChoice = (int)Math.random()*3+1;
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
            }


        }
    }
}


