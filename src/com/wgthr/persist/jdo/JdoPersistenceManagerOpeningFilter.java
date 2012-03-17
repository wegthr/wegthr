package com.wgthr.persist.jdo;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.*;

@Singleton
public class JdoPersistenceManagerOpeningFilter implements Filter {

    private PersistenceManagerFactory pmf;

    @Inject
    private JdoPersistenceManagerProvider pmProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.pmf = JDOHelper.getPersistenceManagerFactory("pmf");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final PersistenceManager pm = pmf.getPersistenceManager();
        try {
            pmProvider.set(pm);
            chain.doFilter(request, response);
        } catch (final IOException e) {
            throw e;
        } catch (final ServletException e) {
            throw e;
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            pm.close();
            pmProvider.set(null);
        }
    }

    @Override
    public void destroy() {
    }
}
