package com.bitcoin.service.botservice;

import com.bitcoin.Utility.Utility;
import com.bitcoin.domain.CoinStrategy;
import com.bitcoin.repository.CoinStrategyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by İlker ÇATAK on 1/20/18.
 */

@Service
public class ImportObjects {
    private final CoinStrategyRepository coinStrategyRepository;
    private final String edoSumValue="0.0000090";
    private final String etcSumValue="0.000015";

    public ImportObjects(CoinStrategyRepository coinStrategyRepository){
        this.coinStrategyRepository=coinStrategyRepository;
    }

//     @Scheduled(fixedDelay = 10000)
    public void importObjectsToDatabase(){
        CoinStrategy coinStrategyEdo= new CoinStrategy();
        coinStrategyEdo.setCoinName("EDO");
        coinStrategyEdo.setSumValue(Utility.convertStringToBigDecimalRoundAsStringLength(edoSumValue));
        coinStrategyEdo.setQuantity(10);
        coinStrategyRepository.save(coinStrategyEdo);

        CoinStrategy coinStrategyEtc= new CoinStrategy();
        coinStrategyEtc.setCoinName("ETC");
        coinStrategyEtc.setSumValue(Utility.convertStringToBigDecimalRoundAsStringLength(etcSumValue));
        coinStrategyEtc.setQuantity(2);
        coinStrategyRepository.save(coinStrategyEtc);

    }
}
