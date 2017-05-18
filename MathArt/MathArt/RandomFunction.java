public class RandomFunction
{
  static Function f;
  public int complexity;
  public boolean red,green,blue,sin,cosine,average,product;
  
  public RandomFunction(boolean r, boolean g, boolean b, boolean s, boolean co, boolean a, boolean p, int c)
  {
    red = r;
    green = g;
    blue = b;
    sin = s;
    cosine = co;
    average = a;
    product = p;
    complexity = c;
    f = randomFunction(r,g,b,s,co,a,p,c);
    
  }
  
  public static void draw(boolean r, boolean g, boolean b, boolean s, boolean co, boolean a, boolean p, int c)
  {
    f = randomFunction(r,g,b,s,co,a,p,c);
    int redCV = 0;
    int greenCV = 0;
    int blueCV = 0;
    System.out.println(f);
    int width = 500;
    int height = 500;
    Draw.window(width,height);
    for(int i = 0; i <= width; i++)
    {
      for(int j = 0; j <= height; j++)
      {
        //i and j between 0 and dim
        //need to convert to decimal between -1 and 1
        //num/dim -> dec. between 0 and 1
        //(num/dim)*2 -> dec. between 0 and 2
        //(num/dim)*2 - 1 -> dec. between -1 and 1
        double scaledx = (1.0*i/width)*2-1;
        double scaledy = (1.0*j/height)*2-1;
        double functionvalue = f.evaluate(scaledx,scaledy);
        //functionvalue will be decimal between -1 and 1
        //need to convert to int between 0 and 255
        //fv*128 -> dec. between -128 and 128
        //(fv*128)+128 -> dec. between 0 and 256
        //casting will round down to int from 0 to 255
        int colorvalue = (int) ((functionvalue*127.5)+127.5);
        //System.out.println("i:"+i+" j:"+j+" scaledx:"+scaledx+" scaledy:"+scaledy+" fv:"+functionvalue+" cv:"+colorvalue);
        if(r == true){
          redCV = colorvalue;
        }
        if(g == true){
          greenCV = colorvalue;
        }
        if(b == true){
          blueCV = colorvalue;
        }
        Draw.setColor(redCV,greenCV,blueCV);
        Draw.rectangle(i, j, 1, 1);
        
      }
    }
  }
  public static double evaluate(double x, double y)
  {
    return f.evaluate(x,y);
  }
  public String toString()
  {
    return f.toString();
  }
  
  public static void main(String[] args) throws InterruptedException
  {
    for(int i = 0; i < 10; i++)
    {
      draw(false,true,false,true,true,true,true,i);
      Thread.sleep(1000);
    }
  }
  
  public static Function randomFunction(boolean r, boolean g, boolean b, boolean s, boolean c, boolean a, boolean p, int complexity)
  {
    double random = Math.random();
    if(complexity == 0)
    {
      if(random < 0.5) return new X();
      else return new Y();
    }
    else
    {
      
      
      
      
      if(random < 0.25) return new Average(randomFunction(r,g,b,s,c,a,p,complexity-1),randomFunction(r,g,b,s,c,a,p,complexity-1));
      else if(random < 0.50) return new Product(randomFunction(r,g,b,s,c,a,p,complexity-1),randomFunction(r,g,b,s,c,a,p,complexity-1));
      else if(random < 0.75) return new Sine(randomFunction(r,g,b,s,c,a,p,complexity-1));
      //else if(random < 0.8) return new Exponential(randomFunction(complexity-1));
      else return new Cosine(randomFunction(r,g,b,s,c,a,p,complexity-1));
    }
    
  }
  
  public int countEquationTrues(){
    int equations = 0;
    if(sin==true){
      equations++;
    }
    if(cosine==true){
      equations++;
    }
    if(average==true){
      equations++;
    }
    if(product==true){
      equations++;
    }
    return equations;
  }
  
  public void setComplexity(int c)
  {
    complexity = c;
  }
  
  public int complexity()
  {
    return complexity;
  }
  public void increaseComplexity()
  {
    complexity++;
  }
  public void decreaseComplexity()
  {
    complexity--;
  }
}