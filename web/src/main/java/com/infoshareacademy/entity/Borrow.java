package com.infoshareacademy.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "borrow")
@IdClass(BorrowPK.class)
public class Borrow {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;


    private Date borrowDate;

    private Date returnDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "book_id")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }


    @Column(name = "borrow_date", nullable = true)
    public Date getBorrowDate() {
        return borrowDate;
    }

/*    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }*/

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }


    @Column(name = "return_date", nullable = true)
    public Date getReturnDate() {
        return returnDate;
    }

    /* public void setReturnDate(Timestamp returnDate) {
         this.returnDate = returnDate;
     }
 */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow that = (Borrow) o;
        return id == that.id &&
                bookId == that.bookId &&
                userId == that.userId &&
                Objects.equals(borrowDate, that.borrowDate) &&
                Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, userId, borrowDate, returnDate);
    }
}