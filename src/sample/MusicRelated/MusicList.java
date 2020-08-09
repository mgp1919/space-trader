package src.sample.MusicRelated;

public class MusicList {

    private static Node current;

    private static Node loFi = new Node("Soothing");
    private static Node dubstep = new Node("Dubstep");
    private static Node jazz = new Node("Jazz");
    private static Node highEnergy = new Node("HighEnergy");
    private static Node aesop = new Node("Aesop");


    public static void initialize() {
        current = loFi;

        loFi.setNext(dubstep);
        loFi.setPrev(aesop);

        dubstep.setNext(jazz);
        dubstep.setPrev(loFi);

        jazz.setNext(highEnergy);
        jazz.setPrev(dubstep);

        highEnergy.setNext(aesop);
        highEnergy.setPrev(jazz);

        aesop.setNext(loFi);
        aesop.setPrev(highEnergy);
    }

    public static String getFileName() {
        return current.getMp3Name();
    }
    public static String getFileNameWithoutTag() {
        return current.getMp3NameWOTag();
    }

    public static void moveTrackForward() {
        current = current.getNext();
    }
    public static void moveTrackBackward() {
        current = current.getPrev();
    }


    public static class Node {
        private Node next;
        private Node prev;
        private String mp3NameWOTag;
        private String mp3Name;

        private Node(String mp3NameWOTag) {
            this.mp3NameWOTag = mp3NameWOTag;
            this.mp3Name = mp3NameWOTag + ".mp3";
        }

        private void setNext(Node next) {
            this.next = next;
        }
        private void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }
        public Node getPrev() {
            return prev;
        }

        public String getMp3Name() {
            return mp3Name;
        }

        public void setMp3NameWOTag(String mp3NameWOTag) {
            this.mp3NameWOTag = mp3NameWOTag;
        }

        public String getMp3NameWOTag() {
            return mp3NameWOTag;
        }
    }
}
