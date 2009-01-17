// THIS IS AN AUTO-GENERATED FILE. DO NOT MODIFY.    
// generated by GenCounter on Fri Jan 16 15:40:38 PST 2009
package scalanlp.counters.shorts;

import scala.collection.mutable.Map;
import scala.collection.mutable.HashMap;

/**
 * Count objects of type Short with type Short.
 * This trait is a wrapper around Scala's Map trait
 * and can work with any scala Map. 
 *
 * @author dlwh
 */
@serializable 
trait Short2ShortCounter extends ShortCounter[Short] {


  abstract override def update(k : Short, v : Short) = {

    super.update(k,v);
  }

  // this isn't necessary, except that the jcl MapWrapper overrides put to call Java's put directly.
  override def put(k : Short, v : Short) :Option[Short] = { val old = get(k); update(k,v); old}

  abstract override def -=(key : Short) = {

    super.-=(key);
  }

  /**
   * Increments the count by the given parameter.
   */
   override  def incrementCount(t : Short, v : Short) = {
     update(t,(this(t) + v).asInstanceOf[Short]);
   }


  override def ++=(kv: Iterable[(Short,Short)]) = kv.foreach(+=);

  /**
   * Increments the count associated with Short by Short.
   * Note that this is different from the default Map behavior.
  */
  override def +=(kv: (Short,Short)) = incrementCount(kv._1,kv._2);

  override def default(k : Short) : Short = 0;

  override def apply(k : Short) : Short = super.apply(k);

  // TODO: clone doesn't seem to work. I think this is a JCL bug.
  override def clone(): Short2ShortCounter  = super.clone().asInstanceOf[Short2ShortCounter]

  /**
   * Return the Short with the largest count
   */
  override  def argmax() : Short = (elements reduceLeft ((p1:(Short,Short),p2:(Short,Short)) => if (p1._2 > p2._2) p1 else p2))._1

  /**
   * Return the Short with the smallest count
   */
  override  def argmin() : Short = (elements reduceLeft ((p1:(Short,Short),p2:(Short,Short)) => if (p1._2 < p2._2) p1 else p2))._1

  /**
   * Return the largest count
   */
  override  def max : Short = values reduceLeft ((p1:Short,p2:Short) => if (p1 > p2) p1 else p2)
  /**
   * Return the smallest count
   */
  override  def min : Short = values reduceLeft ((p1:Short,p2:Short) => if (p1 < p2) p1 else p2)

  // TODO: decide is this is the interface we want?
  /**
   * compares two objects by their counts
   */ 
  override  def comparator(a : Short, b :Short) = apply(a) compare apply(b);

  /**
   * Return a new Short2DoubleCounter with each Short divided by the total;
   */
  override  def normalized() : Short2DoubleCounter = {
    val normalized = Short2DoubleCounter();
    val total : Double = this.total
    if(total != 0.0)
      for (pair <- elements) {
        normalized(pair._1) = pair._2 / total;
      }
    normalized
  }

  /**
   * Return the sum of the squares of the values
   */
  override  def l2norm() : Double = {
    var norm = 0.0
    for (val v <- values) {
      norm += (v * v)
    }
    return Math.sqrt(norm)
  }

  /**
   * Return a List the top k elements, along with their counts
   */
  override  def topK(k : Int) = Counters.topK[(Short,Short)](k,(x,y) => if(x._2 < y._2) -1 else if (x._2 == y._2) 0 else 1)(this);

  /**
   * Return \sum_(t) C1(t) * C2(t). 
   */
  def dot(that : Short2ShortCounter) : Double = {
    var total = 0.0
    for (val (k,v) <- that.elements) {
      total += get(k).asInstanceOf[Double] * v
    }
    return total
  }

  def +=(that : Short2ShortCounter) {
    for(val (k,v) <- that.elements) {
      update(k,(this(k) + v).asInstanceOf[Short]);
    }
  }

  def -=(that : Short2ShortCounter) {
    for(val (k,v) <- that.elements) {
      update(k,(this(k) - v).asInstanceOf[Short]);
    }
  }

  override  def *=(scale : Short) {
    transform { (k,v) => (v * scale).asInstanceOf[Short]}
  }

  override  def /=(scale : Short) {
    transform { (k,v) => (v / scale).asInstanceOf[Short]}
  }
}


object Short2ShortCounter {
  import it.unimi.dsi.fastutil.objects._
  import it.unimi.dsi.fastutil.ints._
  import it.unimi.dsi.fastutil.shorts._
  import it.unimi.dsi.fastutil.longs._
  import it.unimi.dsi.fastutil.floats._
  import it.unimi.dsi.fastutil.doubles._


  import scala.collection.jcl.MapWrapper;
  @serializable
  @SerialVersionUID(1L)
  class FastMapCounter extends MapWrapper[Short,Short] with Short2ShortCounter {
    private val under = new Short2ShortOpenHashMap;
    def underlying() = under.asInstanceOf[java.util.Map[Short,Short]];
    override def apply(x : Short) = under.get(x);
    override def update(x : Short, v : Short) {
      val oldV = this(x);
      updateTotal(v-oldV);
      under.put(x,v);
    }
  }

  def apply() = new FastMapCounter();

  
}

