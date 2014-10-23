package semantics;

public interface IValue {

public enum VType {
  INTEGER, BOOLEAN, VREFERENCE
}

VType typeOf();

String toString();

}


