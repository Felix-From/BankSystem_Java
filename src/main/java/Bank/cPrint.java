package Bank;

import java.util.ArrayList;
import java.util.Objects;

public class cPrint {
    private String Header;
    private ArrayList<BodyContent> Body;
    private String Footer;
    private int fixed_width = 0;
    private cPrintPrefab prefab = new cPrintPrefab();
    private boolean centerFooter = false;

    public cPrint() {
        this.Header = "";
        this.Body = new ArrayList<BodyContent>();
        this.Footer = "";
    }
    //Config

    public void setMinWidth(int width) {
        this.fixed_width = width;
    }

    //Getter Setter

    //Header
    public void setHeader(String header){
        this.Header = header;
    }
    public String getHeader(){
        return this.Header;
    }


    //Body
    public void addBodyContent(BodyContent content){
        this.Body.add(content);
    }

    public boolean removeBodyContent(BodyContent content){
        return this.Body.remove(content);
    }

    //Footer
    public void setFooter(String footer){
        this.Footer = footer;
        this.centerFooter = false;
    }
    public void setFooter(String footer,boolean centerText){
        this.Footer = footer;
        this.centerFooter = centerText;
    }
    public String getFooter(){
        return this.Footer;
    }

    //Style Prefab
    public void setPrefab(cPrintPrefab prefab){
        this.prefab = prefab;
    }
    public cPrintPrefab getPrefab(){
        return this.prefab;
    }

    //Functions

    public void print(){
        int longestLength = this.__getLengthCPrint();

        System.out.println(this.__formatRoof(longestLength,0));

        //Header Print
        System.out.println(this.__formatHeader(longestLength));

        System.out.println(this.__formatRoof(longestLength,1));

        //Body Print
        for(BodyContent body : this.Body){
            System.out.println(this.__formatBodyContent(body,longestLength));
        }


        //Footer
        String Footer = this.__formatFooter(longestLength);
        if(Footer != "")
        {
            System.out.println(this.__formatRoof(longestLength,1));

            System.out.println(Footer);

            System.out.println(this.__formatRoof(longestLength,2));
        } else {
            System.out.println(this.__formatRoof(longestLength,2));
        }

    }

    int __getLengthCPrint(){
        int longestLength = getHeader().length();
        if(this.getFooter().length() > longestLength){
            longestLength = this.getFooter().length();
        }
        for(BodyContent body : this.Body){
            if(body.length() > longestLength){
                longestLength = body.length();
            }
        }
        longestLength += 2; // 2x Abstand zur Wall
        if(longestLength<this.fixed_width)
        {
            longestLength = this.fixed_width;
        }
        return longestLength;
    }

    String __formatHeader(int longestLength){
        int headerSpace = longestLength - this.getHeader().length();
        headerSpace /= 2;
        if(this.getHeader().length() % 2 != 0){
            return this.prefab.border_color+this.prefab.walls+ColorPrint.RESET+" ".repeat(headerSpace)+this.prefab.header_text_color+this.getHeader()+ColorPrint.RESET+" ".repeat(headerSpace+1)+this.prefab.border_color+this.prefab.walls+ColorPrint.RESET;
        } else
        {
            return this.prefab.border_color+this.prefab.walls+ColorPrint.RESET+" ".repeat(headerSpace)+this.prefab.header_text_color+this.getHeader()+ColorPrint.RESET+" ".repeat(headerSpace)+this.prefab.border_color+this.prefab.walls+ColorPrint.RESET;
        }
    }

    String __formatRoof(int longestLength,int Layer){
        if(Layer == 0) {
            return this.prefab.border_color+this.prefab.corners[0]+(this.prefab.roofs.repeat(longestLength))+this.prefab.corners[1]+ColorPrint.RESET;
        }
        else if(Layer == 1) {
            return this.prefab.border_color+this.prefab.roofHolder[0]+(this.prefab.roofs.repeat(longestLength))+this.prefab.roofHolder[1]+ColorPrint.RESET;
        }
        else {
            return this.prefab.border_color+this.prefab.corners[2]+(this.prefab.roofs.repeat(longestLength))+this.prefab.corners[3]+ColorPrint.RESET;

        }


    }

    String __formatBodyContent(BodyContent body,int longestLength){
        return this.prefab.border_color+this.prefab.walls+ColorPrint.RESET+body.printMe(longestLength)+this.prefab.border_color+this.prefab.walls+ColorPrint.RESET;
    }

    String __formatFooter(int longestLength){
        int FooterSpace = (longestLength - this.getFooter().length())/2;
        if(!Objects.equals(this.Footer, ""))
        {
            if(this.centerFooter)
            {
                if(this.getFooter().length() % 2 != 0){
                    return this.prefab.border_color+this.prefab.walls+ColorPrint.RESET+" ".repeat(FooterSpace)+this.prefab.footer_text_color+this.getFooter()+ColorPrint.RESET+" ".repeat(FooterSpace+1)+this.prefab.border_color+this.prefab.walls+ColorPrint.RESET;
                } else
                {
                    return this.prefab.border_color+this.prefab.walls+ColorPrint.RESET+" ".repeat(FooterSpace)+this.prefab.footer_text_color+this.getFooter()+ColorPrint.RESET+" ".repeat(FooterSpace)+this.prefab.border_color+this.prefab.walls+ColorPrint.RESET;
                }
            } else {
                return this.prefab.border_color+this.prefab.walls+ColorPrint.RESET+" "+this.prefab.footer_text_color+this.getFooter()+ColorPrint.RESET+" ".repeat(FooterSpace*2-1)+this.prefab.border_color+this.prefab.walls+ColorPrint.RESET;
            }
        }
        return "";
    }

}

class cPrintPrefab{
    public String[] corners = {"╔", "╗", "╚", "╝"};
    public String[] roofHolder = {"╠","╣"};
    public String roofs = "═";
    public String walls = "║";
    public String border_color = ColorPrint.GREEN.getCode();
    public String header_text_color = ColorPrint.RED.getCode();
    public String body_text_color_1 = ColorPrint.YELLOW.getCode();
    public String body_text_color_2 = ColorPrint.PURPLE.getCode();
    public String body_text_bg_color_1 = ColorPrint.RESET.getCode();
    public String body_text_bg_color_2 = ColorPrint.RESET.getCode();
    public String footer_text_color = ColorPrint.BLUE.getCode();

    void copyThis(cPrintPrefab prefabToCopy){
        this.corners = prefabToCopy.corners;
        this.roofs = prefabToCopy.roofs;
        this.walls = prefabToCopy.walls;
        this.roofHolder = prefabToCopy.roofHolder;
        this.border_color = prefabToCopy.border_color;
        this.header_text_color = prefabToCopy.header_text_color;
        this.body_text_color_1 = prefabToCopy.body_text_color_1;
        this.body_text_color_2 = prefabToCopy.body_text_color_2;
        this.body_text_bg_color_1 = prefabToCopy.body_text_bg_color_1;
        this.body_text_bg_color_2 = prefabToCopy.body_text_bg_color_2;
        this.footer_text_color = prefabToCopy.footer_text_color;
    }

    void toJSON(){
    //todo
    }

}

class BodyContent{
    private String Text1;
    private String Text1_color = ColorPrint.GREEN.getCode();
    private String Text1_bgColor = ColorPrint.BLACK_BACKGROUND.getCode();

    private boolean hasText2;
    private boolean isText2Spaced = true;
    private String Text2;
    private String Text2_color = ColorPrint.GREEN.getCode();
    private String Text2_bgColor = ColorPrint.BLACK_BACKGROUND.getCode();

    public BodyContent(String Text1){
        this.Text1 = Text1;
    }
    public BodyContent(String Text1,ColorPrint Text1Color){
        this.Text1 = Text1;
        this.Text1_color = Text1Color.getCode();
    }
    public BodyContent(String Text1,ColorPrint Text1Color,ColorPrint Text1_BGColor){
        this.Text1 = Text1;
        this.Text1_color = Text1Color.getCode();
        this.Text1_bgColor = Text1_BGColor.getCode();
    }
    public BodyContent(String Text1,String Text2){
        this.Text1 = Text1;
        this.Text2 = Text2;
        this.hasText2 = true;
    }
    public BodyContent(String Text1,ColorPrint Text1Color,String Text2,ColorPrint Text2Color){
        this.Text1 = Text1;
        this.Text1_color = Text1Color.getCode();
        this.Text2 = Text2;
        this.Text2_color = Text2Color.getCode();
        this.hasText2 = true;
    }
    public BodyContent(String Text1,ColorPrint Text1Color,ColorPrint Text1_BGColor,String Text2,ColorPrint Text2Color,ColorPrint Text2_BGColor){
        this.Text1 = Text1;
        this.Text1_color = Text1Color.getCode();
        this.Text1_bgColor = Text1_BGColor.getCode();
        this.Text2 = Text2;
        this.Text2_color = Text2Color.getCode();
        this.Text2_bgColor = Text2_BGColor.getCode();
        this.hasText2 = true;
    }
    public BodyContent(String Text1,String Text2, cPrintPrefab prefab){
        this.Text1 = Text1;
        this.Text1_color = prefab.body_text_color_1;
        this.Text1_bgColor = prefab.body_text_bg_color_1;
        this.Text2 = Text2;
        this.Text2_color = prefab.body_text_color_2;;
        this.Text2_bgColor = prefab.body_text_bg_color_2;
        this.hasText2 = true;
    }

    //Methoden
    void setTextSpacedApart(boolean spaced_between){
        this.isText2Spaced = spaced_between;
    }

    void setPrefab(cPrintPrefab prefab){
        this.Text1_color = prefab.body_text_color_1;
        this.Text1_bgColor = prefab.body_text_bg_color_1;
        this.Text2_color = prefab.body_text_color_2;;
        this.Text2_bgColor = prefab.body_text_bg_color_2;
    }

    int length(){
        int result = this.Text1.length();
        if(this.hasText2) {
            result += this.Text2.length()+1;
        }
        return result;
    }

    String printMe(int longestLength){
        String returnString;
        if(this.hasText2){
            int myLength = longestLength - (this.Text1.length() + this.Text2.length() + 2);
            if(this.isText2Spaced)
            {
                returnString = " "+this.Text1_bgColor+this.Text1_color+this.Text1+ColorPrint.RESET+" ".repeat(myLength)+this.Text2_bgColor+this.Text2_color+this.Text2+ColorPrint.RESET+" ";
            } else
            {
                returnString = " "+this.Text1_bgColor+this.Text1_color+this.Text1+ColorPrint.RESET+" "+this.Text2_bgColor+this.Text2_color+this.Text2+ColorPrint.RESET+" ".repeat(myLength);
            }
        } else {
            returnString = " "+this.Text1_bgColor+this.Text1_color+this.Text1+ColorPrint.RESET+" ".repeat(longestLength-this.Text1.length()-2)+" ";
        }
        return returnString;
    }

}

enum ColorPrint {
    // Text Colors
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    // Background Colors
    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    WHITE_BACKGROUND("\u001B[47m"),

    // Reset code
    RESET("\u001B[0m");

    private final String code;

    ColorPrint(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}

class test{
    public static void main(String[] args) {
        cPrint cprint = new cPrint();

        cPrintPrefab og = new cPrintPrefab();
//        og.walls = "|";
//        og.corners = new String[]{"@", "@", "@", "@"};
//        og.roofHolder = new String[]{"#","#"};
//        og.roofs = "-";
        og.body_text_bg_color_1 = ColorPrint.RESET.getCode();
        og.body_text_bg_color_2 = ColorPrint.RESET.getCode();
        og.border_color = ColorPrint.YELLOW.getCode();
        og.body_text_color_1 = ColorPrint.GREEN.getCode();
        og.body_text_color_2 = ColorPrint.RED.getCode();
        og.footer_text_color = ColorPrint.CYAN.getCode();
        cprint.setPrefab(og);





        cprint.setHeader("Beispiel Header (Zylinder)");
        int r = 2;
        int h = 5;

        BodyContent body1 = new BodyContent("Zylinder","Radius = 2cm , Höhe = 5cm",cprint.getPrefab());
        cprint.addBodyContent(body1);

        double grundflaeche = Math.round(Math.PI*(Math.pow(r,2)));
        BodyContent body2 = new BodyContent("Grundfläche:",grundflaeche+" cm²",cprint.getPrefab());
        cprint.addBodyContent(body2);

        double mantelflaeche = Math.round(2*Math.PI*r*h);
        BodyContent body3 = new BodyContent("Mantelfläche:",mantelflaeche+" cm²",cprint.getPrefab());
        cprint.addBodyContent(body3);

        double oberflaeche = Math.round(2*Math.PI*r*(r*h));
        BodyContent body4 = new BodyContent("Oberfläche:",oberflaeche+" cm²",cprint.getPrefab());
        cprint.addBodyContent(body4);

        double volumen = Math.round(grundflaeche* h);
        BodyContent body5 = new BodyContent("Volumen:",ColorPrint.CYAN,ColorPrint.RESET,volumen+" cm³",ColorPrint.PURPLE,ColorPrint.RESET);
        cprint.addBodyContent(body5);


        cprint.setFooter("Coffmail.de - 2020-2024",true);

        cprint.setMinWidth(50);
        cprint.print();
        System.out.println();
    }
}
