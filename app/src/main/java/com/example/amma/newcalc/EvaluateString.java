package com.example.amma.newcalc;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class EvaluateString
{
    public int flag=0;

   public  int evaluate(String expression)
  {
      char[] tokens = expression.toCharArray();

      Stack<Integer> values = new Stack<>();

      Stack<Character> ops = new Stack<>();

      for (int i = 0; i < tokens.length; i++)
      {

          if (tokens[i] == ' ')
              continue;

           if (Character.isDigit(tokens[i]))
          { StringBuffer sbuf = new StringBuffer();
              while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                  sbuf.append(tokens[i++]);
              values.push(Integer.parseInt(sbuf.toString()));
              i--;
          }
          else if (tokens[i] == '(')
              ops.push(tokens[i]);

          else if (tokens[i] == ')')
          {
              while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
              ops.pop();
          }


          else if (tokens[i] == '+' || tokens[i] == '-' ||
                  tokens[i] == '*' || tokens[i] == '/')
          {
              while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));

              ops.push(tokens[i]);
          }

      }

      while (!ops.empty())
          values.push(applyOp(ops.pop(), values.pop(), values.pop()));
      return values.pop();
  }

  private  boolean hasPrecedence(char op1, char op2)
  {
      if (op2 == '(' || op2 == ')')
          return false;
      if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
          return false;
      else
          return true;
  }

  private  int applyOp(char op, int b, int a)
  {
      switch (op)
      {
          case '+':
              return a + b;
          case '-':
              return a - b;
          case '*':
              return a * b;
          case '/':
              if (b == 0)
                  flag=1;
              else
              return a / b;
      }
      return 0;
  }
}
