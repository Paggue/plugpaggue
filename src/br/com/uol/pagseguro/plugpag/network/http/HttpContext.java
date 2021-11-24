/*    */ package br.com.uol.pagseguro.plugpag.network.http;
/*    */ 
/*    */ import br.com.uol.pagseguro.plugpag.network.NetworkContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpContext
/*    */   extends NetworkContext
/*    */ {
/*    */   public boolean bypassSsl = false;
/* 12 */   public String host = null;
/* 13 */   public int port = 0;
/* 14 */   public String method = "POST";
/* 15 */   public String content = null;
/*    */   
/*    */   public boolean followRedirects = false;
/* 18 */   public byte[] data = null;
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/network/http/HttpContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */