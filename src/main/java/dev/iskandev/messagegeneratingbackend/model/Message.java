package dev.iskandev.messagegeneratingbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.iskandev.messagegeneratingbackend.util.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "messages")
public class Message {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "message_value")
    private String value;
    @Column
    private String sha2;

    public Message() { }

    /**
     * Sets value to new and updates value of {@link #sha2}
     * @param value is new value
     */
    public void updateValue(String value) {
        this.value = value;
        this.sha2 = StringUtils.computeSHA256(this.value);
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getSha2() {
        return sha2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) && Objects.equals(value, message.value) && Objects.equals(sha2, message.sha2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, sha2);
    }
}
