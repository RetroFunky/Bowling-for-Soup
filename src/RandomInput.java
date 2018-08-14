import java.util.Random;
import java.util.Vector;

public class RandomInput {
    private Random rand = new Random();
    private Vector<int> vector = new Vector(11);

    public Vector<int> getVector(){
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
                vector.addElement(rand.nextInt( 11 - vector.lastElement() ) ;
                numarFrame++;
                frameNou = true; // pot sa generez din nou intre 0 si 10
            }
        }
    }
    vector.addElement(rand.nextInt(11)); // adaug 3 elemente random pentru ultimul frame. Unul s-ar putea sa nu fie luat
    vector.addElement(rand.nextInt(11));
    vector.addElement(rand.nextInt(11)); // YOLO

    return vector;
}
