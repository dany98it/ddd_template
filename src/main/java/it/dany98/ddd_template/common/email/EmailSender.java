package it.dany98.ddd_template.common.email;

public interface EmailSender<T extends Message> {
    void send(T message);
}
