package com.aldoborrero.tinder.api.entities;

public interface Match {
    
    Match FOREVER_ALONE = new Match() {
        
        @Override
        public Type getType() {
            return Type.NON_MUTUAL;
        }
        
    };
    
    public enum Type {
        MUTUAL,
        NON_MUTUAL
    }
    
    Type getType();
    
}
