package semantics;

public interface IValue {

public enum VType {
  INTEGER, BOOLEAN, REFERENCE, STRING
}

VType typeOf();

String toString();

}


