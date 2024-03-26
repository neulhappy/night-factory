package org.recorder.nightfactory.repository;


import org.recorder.nightfactory.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    void deleteById(Long id);

}
