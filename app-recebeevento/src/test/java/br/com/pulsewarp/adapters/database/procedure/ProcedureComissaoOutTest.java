package br.com.pulsewarp.adapters.database.procedure;
import br.com.pulsewarp.adapters.config.dbs.SybaseConfiguration;
import br.com.pulsewarp.application.exception.DatabaseExecutionException;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ProcedureComissaoOutTest {

    @Mock
    private SybaseConfiguration sybaseConfiguration;

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private CallableStatement callableStatement;

    @Spy
    private ProcedureComissaoOut procedureComissaoOut;

    @BeforeEach
    void setUp() throws SQLException {
        // Configurando o comportamento do SybaseConfiguration
        when(sybaseConfiguration.dataSource()).thenReturn(dataSource);
        when(dataSource.getConnection()).thenReturn(connection);
    }

    @Test
    void testComissaoPagamento_Success() throws SQLException {
        // Configurando o comportamento para uma execução bem-sucedida
        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        doNothing().when(callableStatement).setString(anyInt(), anyString());
        doNothing().when(callableStatement).setInt(anyInt(), anyInt());
        doNothing().when(callableStatement).execute();

        // Chamando o método em teste
        procedureComissaoOut.comissaoPagamento(new ComandoComissaoPagamento());

        // Verificando as interações
        verify(connection).prepareCall(anyString());
        verify(callableStatement, times(2)).setString(anyInt(), anyString());
        verify(callableStatement).setInt(anyInt(), anyInt());
        verify(callableStatement).execute();
    }

    @Test
    void testComissaoPagamento_ExceptionThrown() throws SQLException {
        // Configurando o comportamento para lançar SQLException
        when(dataSource.getConnection()).thenThrow(SQLException.class);

        // Chamando o método em teste e verificando a exceção
        assertThrows(DatabaseExecutionException.class, () ->
                procedureComissaoOut.comissaoPagamento(new ComandoComissaoPagamento()));

        // Verificando as interações
        verifyNoMoreInteractions(connection, callableStatement);
    }
}
