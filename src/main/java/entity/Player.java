package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name= "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "player1")
    private List<Match> matchesPlayer1= new ArrayList<>();

    @OneToMany(mappedBy = "player2")
    private List<Match> matchesPlayer2= new ArrayList<>();

    @OneToMany(mappedBy = "winner")
    private List<Match> winner= new ArrayList<>();

}

