package semantics;

public interface IValue {

public enum VType {
  INTEGER, BOOLEAN
}

VType typeOf();

String toString();

}


