import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SonglisteGUI extends JFrame {
    Songliste liste = new Songliste();
    Song aktuellerSong = new Song();
    // boolean neuModus;

    // Labels
    private JLabel lTitel = null;
    private JLabel lInterpret = null;
    private JLabel lAlbum = null;
    private JLabel lJahr = null;

    // TextFields
    private JTextField tTitel = null;
    private JTextField tInterpret = null;
    private JTextField tAlbum = null;
    private JTextField tJahr = null;

    // Buttons
    private JButton bErster = null;
    private JButton bVoriger = null;
    private JButton bNaechster = null;
    private JButton bLetzter = null;
    private JButton bNeu = null;
    private JButton bLoeschen = null;
    private JButton bAlleLoeschen = null;

    public SonglisteGUI() {
        // Gibt den Pfad and und liest die Songs
        liste.setPfad("C:\\Users\\massi\\Downloads\\Info\\Work\\Tmp\\src\\tracklist.csv");
        liste.lesenSongs();
        liste.sort();
        liste.schreibenSongs();

        // Titel & Groese
        setTitle("Songliste");
        setBounds(10, 50, 550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Komponenten anlegen

        // Länge Label
        int width = 100;
        // Heohe Label
        int height = 25;
        // x Position für Labels
        int x = 15;

        // Labels
        lTitel = new JLabel("Titel: ");
        lTitel.setBounds(x, 30, width, height);
        lInterpret = new JLabel("Interpret: ");
        lInterpret.setBounds(x, 60, width, height);
        lAlbum = new JLabel("Album: ");
        lAlbum.setBounds(x, 90, width, height);
        lJahr = new JLabel("Jahr: ");
        lJahr.setBounds(x, 120, width, height);

        // Länge TextField
        width = 400;
        // xPosition für TextFields
        x = 115;

        // TextFields
        tTitel = new JTextField();
        tTitel.setBounds(x, 30, width, height);
        tInterpret = new JTextField();
        tInterpret.setBounds(x, 60, width, height);
        tAlbum = new JTextField();
        tAlbum.setBounds(x, 90, width, height);

        // Länge TextField Jahr
        width = 70;

        tJahr = new JTextField();
        tJahr.setBounds(x, 120, width, height);

        // Länge Buttons obere Reihe
        width = 125;
        // Größe Buttons obere Reihe
        height = 35;
        // yPostion Buttons obere Reihe
        int y = 155;

        // --------------------
        // Buttons obere Reihe
        // --------------------
        bErster = new JButton();
        bErster.setText("Erster");
        bErster.setBounds(15, y, width, height);

        bVoriger = new JButton();
        bVoriger.setText("Voriger");
        bVoriger.setBounds(15 + width, y, width, height);

        bNaechster = new JButton();
        bNaechster.setText("Nächster");
        bNaechster.setBounds(15 + width * 2, y, width, height);

        bLetzter = new JButton();
        bLetzter.setText("Letzter");
        bLetzter.setBounds(15 + width * 3, y, width, height);

        // Länge Buttons untere Reihe
        width = 167;
        // yPosition Buttons untere Reihe
        y = 195;

        // ---------------------
        // Buttons untere Reihe
        // ---------------------
        bNeu = new JButton();
        bNeu.setText("Neu");
        bNeu.setBounds(15, y, width, height);

        bLoeschen = new JButton();
        bLoeschen.setText("Löschen");
        bLoeschen.setBounds(15 + width, y, width, height);

        bAlleLoeschen = new JButton();
        bAlleLoeschen.setText("Alle Löschen");
        bAlleLoeschen.setBounds(15 + width * 2, y, width, height);

        // Container erstellen
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        // Labels
        contentPane.add(lTitel);
        contentPane.add(lInterpret);
        contentPane.add(lAlbum);
        contentPane.add(lJahr);
        // textfields
        contentPane.add(tTitel);
        contentPane.add(tInterpret);
        contentPane.add(tAlbum);
        contentPane.add(tJahr);
        // buttons
        contentPane.add(bErster);
        contentPane.add(bVoriger);
        contentPane.add(bNaechster);
        contentPane.add(bLetzter);
        contentPane.add(bNeu);
        contentPane.add(bLoeschen);
        contentPane.add(bAlleLoeschen);

        setVisible(true);

        aktuellerSong = liste.getErster();
        setTextfields();

        // --------------------
        // BUTTONS
        // --------------------

        /**
         * Setzt den ersten Song von der Liste
         */
        bErster.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    aktuellerSong = liste.getErster();
                    setTextfields();
                } catch (NumberFormatException e) {
                    setFeler();
                }
            }

        });

        /**
         * Setzt den Vorhergehenden Song von der Liste
         */
        bVoriger.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    aktuellerSong = liste.getVorherigen();
                    setTextfields();
                } catch (NumberFormatException e) {
                    setFeler();
                }
            }

        });

        /**
         * Setzt den naechsten Song von der Liste
         */
        bNaechster.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    aktuellerSong = liste.getNaechster();
                    setTextfields();
                } catch (NumberFormatException e) {
                    setFeler();
                }
            }

        });

        /**
         * Setzt den letzten Song von der Liste
         */
        bLetzter.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    aktuellerSong = liste.getLetzter();
                    setTextfields();
                } catch (NumberFormatException e) {
                    setFeler();
                }
            }

        });

        /**
         * Erstesst einen neuen Song am ende der Liste
         */
        bNeu.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    // Werte einlesen
                    Song tmp = new Song();
                    tmp.setTitel(tTitel.getText());
                    tmp.setInterpret(tInterpret.getText());
                    tmp.setAlbum(tAlbum.getText());
                    try {
                        tmp.setErscheinungsjahr(Integer.parseInt(tJahr.getText()));
                    } catch (NumberFormatException e) {
                        tmp.setErscheinungsjahr(-1);
                    }

                    // falls es anders ist Song anlegen
                    if (!tmp.equals(liste.getAktueller())) {
                        liste.anfuegenNeuen(tmp);
                        aktuellerSong = liste.getAktueller();
                        liste.schreibenSongs();
                        setTextfields();
                    }
                } catch (NumberFormatException e) {
                    setFeler();
                }
            }

        });

        /**
         * Loescht den aktuellen song von der Liste
         */
        bLoeschen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                try {
                    aktuellerSong = liste.loeschenAktuellen();
                    liste.schreibenSongs();
                    setTextfields();
                } catch (NumberFormatException e) {
                    setFeler();
                }
            }
        });

        /**
         * Loescht alle elemente von der liste
         */
        bAlleLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    liste.loeschenAlle();
                    aktuellerSong = liste.getAktueller();
                    liste.schreibenSongs();
                    setTextfields();
                } catch (NumberFormatException e) {
                    setFeler();
                }
            }
        });

    }

    /**
     * Giebt den Song auf die Textfelder aus
     */
    public void setTextfields() {
        if (aktuellerSong != null) {
            tTitel.setText(aktuellerSong.getTitel());
            tInterpret.setText(aktuellerSong.getInterpret());
            tAlbum.setText(aktuellerSong.getAlbum());
            tJahr.setText(String.valueOf(aktuellerSong.getErscheinungsjahr()));
        } else {
            tTitel.setText("");
            tInterpret.setText("");
            tAlbum.setText("");
            tJahr.setText("");
        }
    }

    /**
     * Setzt die Textfelder auf "Fehler"
     */
    public void setFeler() {
        if (aktuellerSong != null) {
            tTitel.setText("Fehler");
            tInterpret.setText("Fehler");
            tAlbum.setText("Fehler");
            tJahr.setText("Fehler");
        }
    }
}
