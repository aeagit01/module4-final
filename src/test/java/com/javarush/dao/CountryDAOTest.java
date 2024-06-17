package com.javarush.dao;

import com.javarush.domain.Country;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CountryDAOTest {
    private CountryDAO countryDAO;
    private SessionFactory sessionFactory;
    private Session session;
    private Query<Country> query;

    @BeforeEach
    void setUp() {
        sessionFactory = mock(SessionFactory.class);
        countryDAO = new CountryDAO(sessionFactory);
        session = mock(Session.class);
        query = mock(Query.class);
    }

    @Test
    void testGetAll_ReturnsListOfCountries() {

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("select c from Country c", Country.class)).thenReturn(query);
        when(query.list()).thenReturn(List.of(new Country(), new Country()));

        List<Country> result = countryDAO.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
}