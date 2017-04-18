package ir.zeroandone.app.infra.transformer;

public interface Transformer<O,U> {
    U transform(O jsonString) throws Exception;
}
