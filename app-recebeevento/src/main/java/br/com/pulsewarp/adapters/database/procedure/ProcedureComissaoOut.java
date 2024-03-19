package br.com.pulsewarp.adapters.database.procedure;

import br.com.pulsewarp.adapters.config.dbs.SybaseConfiguration;
import br.com.pulsewarp.application.exception.DatabaseExecutionException;
import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ProcedureComissaoOut implements ProcedureComissaoIn {

    private final SybaseConfiguration dataSource;
    private static final Logger logger = LoggerFactory.getLogger(ProcedureComissaoOut.class);

    @Override
    public void comissaoPagamento(ComandoComissaoPagamento comissaoPagamento) {
        try (Connection conn = dataSource.dataSource().getConnection();
             CallableStatement cstmt = conn.prepareCall("{call inserir_comissao_estorno(?, ?, ?, ?, ?)}")) {

            cstmt.setString(1, "valor1");
            cstmt.setInt(2, 123);

            cstmt.execute();
        } catch (SQLException e) {
            logger.error("Ocorreu um erro ao executar comissao de pagamento : ", e);
            throw new DatabaseExecutionException("Erro ao executar comissao de pagamento", e);
        }
    }

    @Override
    public void comissaoEstorno(ComandoComissaoEstorno comissaoEstorno) {
        try (Connection conn = dataSource.dataSource().getConnection();
             CallableStatement cstmt = conn.prepareCall("{call inserir_comissao_estorno(?, ?, ?, ?, ?)}")) {

            cstmt.setString(1, comissaoEstorno.getNumContrato());
            cstmt.setString(2, comissaoEstorno.getValorComVista());
            cstmt.setString(3, comissaoEstorno.getDataContrato());
            cstmt.setString(4, comissaoEstorno.getDataEstorno());
            cstmt.setString(5, comissaoEstorno.getSiglaOrigem());

            cstmt.execute();
        } catch (SQLException e) {
            logger.error("Ocorreu um erro ao executar comissao de estorno : ", e);
            throw new DatabaseExecutionException("Erro ao executar comissao de estorno", e);
        }
    }
}
