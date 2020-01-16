package com.infoshareacademy.dao;

import com.infoshareacademy.domain.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class BookDao {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @PersistenceContext
    private EntityManager em;

    public void addBook(Book book) {

        em.persist(book);
        logger.info("New book was added :{}", book);

    }
    public Book findById(Long id){

        Query query = em.createNamedQuery("Book.getById");
        query.setParameter("id",id);
        return (Book)query.getSingleResult();
    }

    public Book findByTitle(){



        return null;
    }

}