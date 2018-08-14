import java.util.Random;
import java.util.Vector;
import java.lang.Integer;

public class RandomInput {
    private Random rand ;
    private Vector<Integer> vector ;


    public RandomInput() { // constructor
        rand = new Random();
        vector = new Vector(11) ;

    }

    // Metoda care genereaza un "joc" random de bowling
    public Vector<Integer> getVector(){
        int numarFrame = 1;
        boolean frameNou = true ;
        while (numarFrame < 10) {
            if (frameNou) { // pot sa generez intre 0 si 10
                vector.addElement(rand.nextInt(11)) ;
                if (vector.lastElement() == 10){
                    numarFrame++;
                }
                else {
                    frameNou = false;
                }
            }
            else {  // pot sa generez intre 0 si 10 - ultimul numar aruncat
                vector.addElement(rand.nextInt( 11 - vector.lastElement() )) ;
                numarFrame++;
                frameNou = true; // pot sa generez din nou intre 0 si 10
            }
        }
    int penultimul = rand.nextInt(11) ;
        if ( penultimul == 10) {
            vector.addElement(penultimul); // adaug 3 elemente random pentru ultimul frame. Unul s-ar putea sa nu fie luat
            vector.addElement(rand.nextInt(11));
            vector.addElement(rand.nextInt(11 - vector.lastElement())); // comentarii irelevante! Ma grabesc
        }
        else {
            int ultimul = rand.nextInt(11 - penultimul) ;
            vector.addElement(penultimul);
            vector.addElement(ultimul);
            if ( ultimul + penultimul == 10)
                vector.addElement(rand.nextInt(11));

        }
    return vector;
    }
}