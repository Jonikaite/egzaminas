package lt.techin.egzaminas.model;

import javax.persistence.*;
import javax.persistence.Book;

    public class SubBook {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private lt.techin.from.scratch.model.Book book;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public lt.techin.from.scratch.model.Book getBook() {
            return book;
        }

        public void setBook(lt.techin.from.scratch.model.Book book) {
            this.book = book;
        }
    }
}
