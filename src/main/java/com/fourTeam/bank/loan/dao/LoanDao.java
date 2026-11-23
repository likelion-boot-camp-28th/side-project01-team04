package com.fourTeam.bank.loan.dao;

import com.fourTeam.bank.loan.model.dto.LoanDto;
import com.fourTeam.bank.loan.model.dto.LoanProductDto;

import java.util.ArrayList;
import java.util.List;

// 대출 상품과 대출 계약을 List에 보관해줌
public class LoanDao {
    private List<LoanProductDto> product = new ArrayList<>();        // 대출상품 리스트
    private List<LoanDto> loans = new ArrayList<>();
    private int loanSeq;                                        // 대출번호

    LoanProductDto L01 = new LoanProductDto("L01", "새싹 소액대출",0.08,5000000,6,12,"만기일시",10);
    LoanProductDto L02 = new LoanProductDto("L02", "직장인 신용대출",0.05,30000000,12,36,"원리금균등",6);
    LoanProductDto L03 = new LoanProductDto("L03", "우수고객 대출",0.04,50000000,12,60,"원금균등",3);

    public LoanDao() {
        product.add(L01);
        product.add(L02);
        product.add(L03);
    }

    // 대출 상품 전체 조회함
    public List<LoanProductDto> findAllProducts(){
        return product;
    }

    // 상품 하나 찾기
    public LoanProductDto findProductByCode(String code){
        for(int i=0; i<product.size();i++){
            if(product.get(i).getProductCode().equals(code)){   // 코드가 일치하면
                return product.get(i);                          // 그 코드를 가진 상품을 리턴하기
            }
        }
        return null;
    }

    // 대출 저장 , 새로 실행된 대출 계약을 저장함
    public void save(LoanDto loan){
        loans.add(loan);
    }

    // 대출 하나 찾기, 대출 번호로 대출 계약 하나를 찾아줌
    public LoanDto findByLoanNo(String loanNo){
        for(int i=0; i<loans.size(); i++){
            if(loans.get(i).getLoanNo().equals(loanNo)){
                return loans.get(i);
            }
        }
        return null;
    }

    // 회원의 대출 목록
    public List<LoanDto> findByMemberId(String memberId){
        List<LoanDto> result = new ArrayList<>(); // 리턴할 결과, 일단은 빈 바구니

        for(int i=0; i<loans.size(); i++){
            if(loans.get(i).getMemberId().equals(memberId)){
                result.add(loans.get(i));
            }
        }

        return result;
    }

    // 새 대출번호 발급함
   public String nextLoanNo(){
        loanSeq +=1;
        return String.format("LN%03d", loanSeq);    // LN001이런식으로 포맷형식을 지정해줌
    }

}
