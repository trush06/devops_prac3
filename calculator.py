#!/usr/bin/env python3
# Python Program - Calculator

def add(a, b):
    return a + b

def subtract(a, b):
    return a - b

def multiply(a, b):
    return a * b

def divide(a, b):
    if b == 0:
        return "Error: Division by zero"
    return a / b

def main():
    print("=" * 50)
    print("Python Calculator Program")
    print("=" * 50)
    
    try:
        # Test operations
        num1 = 50
        num2 = 30
        
        print(f"Number 1: {num1}")
        print(f"Number 2: {num2}")
        print("-" * 50)
        
        print(f"Addition: {num1} + {num2} = {add(num1, num2)}")
        print(f"Subtraction: {num1} - {num2} = {subtract(num1, num2)}")
        print(f"Multiplication: {num1} * {num2} = {multiply(num1, num2)}")
        print(f"Division: {num1} / {num2} = {divide(num1, num2):.2f}")
        
        print("=" * 50)
        print("Calculation completed successfully!")
        print("=" * 50)
        
    except Exception as e:
        print(f"Error occurred: {e}")
        return 1
    
    return 0

if __name__ == "__main__":
    main()
